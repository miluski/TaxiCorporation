package com.projects.taxicorporation.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RenameOrDeleteDepartmentController implements Controller {
    @FXML
    public AnchorPane buttonsAnchorPane;
    @FXML
    public TextField departmentNameField;
    @FXML
    public TextField departmentCityField;
    @FXML
    public TextField departmentStreetField;
    @FXML
    public ChoiceBox<String> departmentChoiceBox;
    public List<String> numberOfDepartmentIds;

    public void onAddMenagerButtonClicked() throws Exception {
        FormFactory formFactory = new AddManagerFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onDeleteMenagerButtonClicked() throws Exception {
        FormFactory formFactory = new DeleteManagerFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onManageMenagersButtonClicked() throws Exception {
        FormFactory formFactory = new ManageManagerFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onAddDepartmentButtonClicked() throws Exception {
        FormFactory formFactory = new AddDepartmentFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onAddCarButtonClicked() throws Exception {
        FormFactory formFactory = new AddCarFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onDeleteCarButtonClicked() throws Exception {
        FormFactory formFactory = new DeleteCarFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onLogoutButtonClicked() {
        UserOperations userOperations = new UserOperations();
        UserFacade userFacade = new UserFacade(MainStage.getInstance().getUser(), userOperations);
        userFacade.logOutUser();
    }

    public void onConfirmDepartmentEditButton() {
        if(validateDepartmentStreet() && validateDepartmentCity() && validateDepartmentName())
            communicateWithServer("RenameDepartment");
        else
            AlertDialog.getInstance().setParametersAndShow("Wprowadzone dane są nieprawidłowe!", Alert.AlertType.ERROR);
    }

    public void onDeleteChoosedDepartmentButtonClicked() {
        communicateWithServer("DeleteDepartment");
    }

    public boolean validateDepartmentName() {
        String departmentName = departmentNameField.getText();
        try {
            Integer.parseInt(departmentName);
        } catch (Exception e) {
            return departmentName.length() >= 4;
        }
        return false;
    }

    private boolean validateDepartmentCity() {
        String departmentCity = departmentCityField.getText();
        try {
            Integer.parseInt(departmentCity);
        } catch (Exception e) {
            return departmentCity.length() >= 6;
        }
        return false;
    }

    private boolean validateDepartmentStreet() {
        String departmentStreet = departmentStreetField.getText();
        try {
            Integer.parseInt(departmentStreet);
        } catch (Exception e) {
            return departmentStreet.length() >= 5;
        }
        return false;
    }

    public void onChangeThemeButtonClicked() throws Exception {
        if (Objects.equals(MainStage.getInstance().getThemeName(), "Light")) {
            new DarkTheme().applyTheme();
        } else {
            new LightTheme().applyTheme();
        }
        FormFactory formFactory = new RenameOrDeleteDepartmentFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    protected void fetchDepartmentsData() {
        communicateWithServer("GetDepartments");
    }

    private void communicateWithServer(String operationName) {
        try (Socket socket = new Socket("localhost", 1523)) {
            sendOperationName(socket, operationName);
            sendData(socket, operationName);
            receiveFeedback(socket);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendOperationName(Socket socket, String operationName) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        int operationStringLength = operationName.length();
        byte[] messageByteArray = operationName.getBytes(StandardCharsets.UTF_8);
        outputStream.write(operationStringLength);
        outputStream.flush();
        outputStream.write(messageByteArray);
        outputStream.flush();
    }

    private void sendData(Socket socket, String operationName) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        List<String> dataList = new ArrayList<>();
        if (validateDepartmentCity() && validateDepartmentName() && validateDepartmentStreet()) {
            dataList.add(numberOfDepartmentIds.get(departmentChoiceBox.getSelectionModel().getSelectedIndex()));
            if(operationName.equals("RenameDepartment")) {
                dataList.add(departmentCityField.getText());
                dataList.add(departmentNameField.getText());
                dataList.add(departmentStreetField.getText());
            }
        } else dataList.add("");
        oos.writeObject(dataList);
        dataList.clear();
        byte[] dataByteArray = bos.toByteArray();
        int dataByteArrayLength = dataByteArray.length;
        outputStream.write(dataByteArrayLength);
        outputStream.flush();
        outputStream.write(dataByteArray);
        outputStream.flush();
        oos.reset();
        bos.reset();
    }

    private void receiveFeedback(Socket socket) throws Exception {
        InputStream inputStream = socket.getInputStream();
        int receivedBytesSize = inputStream.read();
        byte[] receivedBytes = new byte[receivedBytesSize];
        Object receivedObject = new ObjectInputStream(new ByteArrayInputStream(receivedBytes, 0, inputStream.read(receivedBytes))).readObject();
        List<String> data = new ArrayList<>((List<String>) receivedObject);
        if (data.isEmpty())
            AlertDialog.getInstance().setParametersAndShow("Wystąpił nieoczekiwany błąd!", Alert.AlertType.ERROR);
        else {
            String response = data.get(0);
            switch (response) {
                case "EditDepartmentSuccessfull" -> {
                    AlertDialog.getInstance().setParametersAndShow("Pomyślnie edytowano szczegóły oddziału!", Alert.AlertType.INFORMATION);
                    departmentChoiceBox.getItems().removeAll();
                    fetchDepartmentsData();
                }
                case "DeleteDepartmentSuccessfull" -> {
                    AlertDialog.getInstance().setParametersAndShow("Pomyślnie usunięto wybrany oddział!", Alert.AlertType.INFORMATION);
                    departmentChoiceBox.getItems().removeAll();
                    fetchDepartmentsData();
                }
                case "FetchedDepartments" -> {
                    data.remove(0);
                    if (data.isEmpty()) data.add("Brak oddziałów");
                    List<String> filteredData = data.stream()
                            .filter(item -> !item.matches("\\d+"))
                            .filter(item -> !item.startsWith("Street: "))
                            .collect(Collectors.toList());
                    numberOfDepartmentIds = data.stream()
                            .filter(item -> item.matches("\\d+"))
                            .collect(Collectors.toList());
                    ObservableList<String> observableList = FXCollections.observableArrayList(filteredData);
                    departmentChoiceBox.setItems(observableList);
                    departmentChoiceBox.getSelectionModel().select(0);
                    List<String> filteredStreets = data.stream().filter(item -> item.startsWith("Street: ")).toList();
                    setDepartmentFields(filteredData, filteredStreets, 0);
                    departmentChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov, value, new_value) -> {
                        int index = departmentChoiceBox.getSelectionModel().getSelectedIndex();
                        setDepartmentFields(filteredData, filteredStreets, index);
                    });
                }
                case "EditDepartmentUnsuccessfull", "DeleteDepartmentUnsuccessfull" ->
                        AlertDialog.getInstance().setParametersAndShow("Wystąpił błąd przy zarządzaniu wybranym oddziałem!", Alert.AlertType.ERROR);
            }
        }
    }
    private void setDepartmentFields(List<String> filteredData, List<String> filteredStreets, int index) {
        String departmentName = (filteredData.get(index).substring(0, filteredData.get(index).indexOf(",")));
        String departmentCity = (filteredData.get(index).substring(filteredData.get(index).indexOf(",") + 2));
        String departmentStreet = (filteredStreets.get(index).substring(8));
        departmentNameField.setText(departmentName);
        departmentCityField.setText(departmentCity);
        departmentStreetField.setText(departmentStreet);
    }
    @Override
    public AnchorPane getButtonsAnchorPane() {
        return this.buttonsAnchorPane;
    }

}
