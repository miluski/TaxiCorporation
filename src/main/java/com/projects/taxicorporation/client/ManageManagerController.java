package com.projects.taxicorporation.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ManageManagerController implements Controller {
    @FXML
    private AnchorPane buttonsAnchorPane;
    @FXML
    private ChoiceBox<String> managerChoiceBox;
    @FXML
    private ChoiceBox<String> departmentChoiceBox;
    private List<String> numberOfManagerIds;
    private List<String> numberOfDepartmentStreets;

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

    public void onAddDepartmentButtonClicked() throws Exception {
        FormFactory formFactory = new AddDepartmentFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onDeleteDepartmentButtonClicked() throws Exception {
        FormFactory formFactory = new RenameOrDeleteDepartmentFactory();
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

    public void onChangeThemeButtonClicked() throws Exception {
        if (Objects.equals(MainStage.getInstance().getThemeName(), "Light")) {
            new DarkTheme().applyTheme();
        } else {
            new LightTheme().applyTheme();
        }
        FormFactory formFactory = new ManageManagerFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onEndAssignMenagerButtonClicked() {
        communicateWithServer("AssignManagerDepartment");
    }

    protected void fetchDepartmentsData() {
        communicateWithServer("GetDepartments");
    }

    protected void fetchManagersData() {
        communicateWithServer("GetManagers");
    }

    private void communicateWithServer(String operationName) {
        try (Socket socket = new Socket("localhost", 1523)) {
            sendOperationName(socket, operationName);
            sendData(socket);
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

    private void sendData(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        List<String> dataList = new ArrayList<>();
        if (departmentChoiceBox.getValue() != null && managerChoiceBox.getValue() != null) {
            dataList.add(departmentChoiceBox.getValue().substring(0, departmentChoiceBox.getValue().indexOf(",")));
            dataList.add(departmentChoiceBox.getValue().substring(departmentChoiceBox.getValue().indexOf(",") + 2));
            dataList.add(numberOfDepartmentStreets.get(departmentChoiceBox.getSelectionModel().getSelectedIndex()).replace("Street: ", ""));
            dataList.add(numberOfManagerIds.get(managerChoiceBox.getSelectionModel().getSelectedIndex()));
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
                case "AssignManagerDepartmentSuccessfull" -> {
                    AlertDialog.getInstance().setParametersAndShow("Pomyślnie przydzielono nowy oddział dla wybranego menadżera!", Alert.AlertType.INFORMATION);
                    managerChoiceBox.getItems().removeAll();
                    departmentChoiceBox.getItems().removeAll();
                    fetchManagersData();
                    fetchDepartmentsData();
                }
                case "FetchedDepartments" -> {
                    data.remove(0);
                    if (data.isEmpty()) data.add("Brak oddziałów");
                    List<String> filteredData = data.stream()
                            .filter(item -> !item.matches("\\d+"))
                            .filter(item -> !item.startsWith("Street: "))
                            .collect(Collectors.toList());
                    numberOfDepartmentStreets = data.stream().
                            filter(item -> item.startsWith("Street: ")).
                            collect(Collectors.toList());
                    ObservableList<String> observableList = FXCollections.observableArrayList(filteredData);
                    departmentChoiceBox.setItems(observableList);
                    departmentChoiceBox.getSelectionModel().select(0);
                }
                case "FetchedManagers" -> {
                    data.remove(0);
                    if (data.isEmpty()) data.add("Brak kont menadżerskich");
                    List<String> filteredData = data.stream()
                            .filter(item -> !item.matches("\\d+"))
                            .collect(Collectors.toList());
                    numberOfManagerIds = data.stream()
                            .filter(item -> item.matches("\\d+"))
                            .collect(Collectors.toList());
                    ObservableList<String> observableList = FXCollections.observableArrayList(filteredData);
                    managerChoiceBox.setItems(observableList);
                    managerChoiceBox.getSelectionModel().select(0);
                }
                case "AssignManagerDepartmentUnsuccessfull" ->
                        AlertDialog.getInstance().setParametersAndShow("Wystąpił błąd przy przydzielaniu wybranego oddziału dla menadżera!", Alert.AlertType.ERROR);
            }
        }
    }

    @Override
    public AnchorPane getButtonsAnchorPane() {
        return this.buttonsAnchorPane;
    }

}
