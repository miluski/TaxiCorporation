package com.projects.taxicorporation.client;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddDepartmentController implements Controller {
    @FXML
    private TextField departmentNameField;
    @FXML
    private TextField departmentCityField;
    @FXML
    private TextField departmentStreetField;
    @FXML
    private AnchorPane buttonsAnchorPane;

    @FXML
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

    public void onAddCarButtonClicked() throws Exception {
        FormFactory formFactory = new AddCarFactory();
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

    public void onLogoutButtonClicked() {
        UserOperations userOperations = new UserOperations();
        UserFacade userFacade = new UserFacade(MainStage.getInstance().getUser(), userOperations);
        userFacade.logOutUser();
    }

    public void onEndAddDepartmentButtonClicked() {
        if (validateDepartmentName() && validateDepartmentCity() && validateDepartmentStreet())
            communicateWithServer();
        else
            AlertDialog.getInstance().setParametersAndShow("Wprowadzone dane są nieprawidłowe!", Alert.AlertType.ERROR);
    }

    public void onEditOrDeleteDepartmentButtonClicked() throws Exception {
        FormFactory formFactory = new RenameOrDeleteDepartmentFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    private boolean validateDepartmentName() {
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

    private void communicateWithServer() {
        try (Socket socket = new Socket("localhost", 1523)) {
            sendOperationName(socket);
            sendData(socket);
            receiveFeedback(socket);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendOperationName(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        String operation = "AddDepartment";
        int operationStringLength = operation.length();
        byte[] messageByteArray = operation.getBytes(StandardCharsets.UTF_8);
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
        dataList.add(departmentCityField.getText());
        dataList.add(departmentStreetField.getText());
        dataList.add(departmentNameField.getText());
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
            if(response.equals("AddDepartmentSuccessfull")) {
                AlertDialog.getInstance().setParametersAndShow("Pomyślnie dodano nowy oddział!", Alert.AlertType.INFORMATION);
                departmentCityField.setText("");
                departmentNameField.setText("");
                departmentStreetField.setText("");
            }
            else
                AlertDialog.getInstance().setParametersAndShow("Wystąpił błąd przy dodawaniu nowego oddziału!", Alert.AlertType.ERROR);
        }
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
        FormFactory formFactory = new AddDepartmentFactory();
        Form form = formFactory.createForm();
        form.start();
    }
}
