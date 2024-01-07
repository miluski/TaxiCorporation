package com.projects.taxicorporation.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddCarController implements Controller {
    @FXML
    private TextField carModelField;
    @FXML
    private TextField yearOfProductionField;
    @FXML
    private ChoiceBox<String> departmentChoiceBox;
    @FXML
    private AnchorPane buttonsAnchorPane;
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
    public void onMenageMenagersButtonClicked() throws Exception {
        FormFactory formFactory = new ManageManagerFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onAddDepartmentButtonClicked() throws Exception {
        FormFactory formFactory = new AddDepartmentFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onMenageDepartmentsButtonClicked() throws Exception {
        FormFactory formFactory = new RenameOrDeleteDepartmentFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onDeleteCarButtonClicked() throws Exception {
        FormFactory formFactory = new DeleteCarFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLogoutButtonClicked() throws Exception  {
        UserOperations userOperations = new UserOperations();
        UserFacade userFacade = new UserFacade(MainStage.getInstance().getUser(), userOperations);
        userFacade.logOutUser();
    }
    public void onEndAddCarButtonClicked() {
        boolean isDataValid = validateCarModel() && validateYearOfProduction() &&
                departmentChoiceBox.getValue() != null;
        if(isDataValid) {
            communicateWithServer("AddCar");
        }
        else
            AlertDialog.getInstance().setParametersAndShow("Wprowadzone dane są nieprawidłowe!", Alert.AlertType.ERROR);
    }
    private boolean validateCarModel() {
        String carModel = carModelField.getText();
        return carModel.length() >= 5;
    }
    private boolean validateYearOfProduction() {
        boolean isValid;
        String yearOfProduction = yearOfProductionField.getText();
        try {
            Integer.parseInt(yearOfProduction);
            isValid = (yearOfProduction.length() == 4);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            isValid = false;
        }
        return isValid;
    }

    protected void fetchDepartmentsData() {
        communicateWithServer("GetDepartments");
    }

    @Override
    public AnchorPane getButtonsAnchorPane() {
        return this.buttonsAnchorPane;
    }

    public void onChangeThemeButtonClicked() throws Exception {
        if (Objects.equals(MainStage.getInstance().getThemeName(), "Light")) {
            new DarkTheme().applyTheme();
        } else {
            new LightTheme().applyTheme();
        }
        FormFactory formFactory = new AddCarFactory();
        Form form = formFactory.createForm();
        form.start();
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
        dataList.add(carModelField.getText());
        dataList.add(yearOfProductionField.getText());
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
                case "AddCarSuccessfull" -> {
                    AlertDialog.getInstance().setParametersAndShow("Pomyślnie dodano nowy samochód!", Alert.AlertType.INFORMATION);
                    carModelField.setText("");
                    yearOfProductionField.setText("");
                }
                case "FetchedDepartments" -> {
                    data.remove(0);
                    ObservableList<String> observableList = FXCollections.observableArrayList(data);
                    departmentChoiceBox.setItems(observableList);
                    departmentChoiceBox.getSelectionModel().select(0);
                }
                case "AddCarUnsuccessfull" ->
                        AlertDialog.getInstance().setParametersAndShow("Wystąpił błąd przy dodawaniu nowego samochodu!", Alert.AlertType.ERROR);
            }
        }
    }
}
