package com.projects.taxicorporation.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AddDriverController implements Controller {
    @FXML
    private AnchorPane buttonsAnchorPane;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ChoiceBox<String> departmentChoiceBox;
    public void onHireDriverButtonClicked() throws Exception {
        FormFactory formFactory = new DeleteDriverFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onManageDriversButtonClicked() throws Exception {
        FormFactory formFactory = new ManageDriversFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onMapButtonClicked() throws Exception {
        FormFactory formFactory = new ShowManagerMapFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLogoutButtonClicked() {
        UserOperations userOperations = new UserOperations();
        UserFacade userFacade = new UserFacade(MainStage.getInstance().getUser(), userOperations);
        userFacade.logOutUser();
    }
    public void onEndDriverAddButton() {
        communicateWithServer("RegisterTask");
    }
    protected void fetchDepartmentsData() {
        communicateWithServer("GetDepartments");
    }

    private void communicateWithServer(String operationName) {
        try (Socket socket = new Socket("localhost", 1523)) {
            sendOperationName(socket, operationName);
            switch (operationName) {
                case "GetDepartments" -> {
                    sendData(socket);
                    receiveFeedback(socket);
                }
                case "RegisterTask" -> handleData(socket);
            }
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

    public void onChangeThemeButtonClicked() throws Exception {
        if (Objects.equals(MainStage.getInstance().getThemeName(), "Light")) {
            new DarkTheme().applyTheme();
        } else {
            new LightTheme().applyTheme();
        }
        FormFactory formFactory = new AddDriverFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    private void sendData(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        List<String> dataList = new ArrayList<>();
        dataList.add("");
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
            if (response.equals("FetchedDepartments")) {
                data.remove(0);
                if (data.isEmpty()) data.add("Brak oddziałów");
                List<String> filteredData = data.stream().
                        filter(item -> !item.matches("\\d+")).
                        filter(item -> !item.startsWith("Street: ")).
                        collect(Collectors.toList());
                ObservableList<String> observableList = FXCollections.observableArrayList(filteredData);
                departmentChoiceBox.setItems(observableList);
                departmentChoiceBox.getSelectionModel().select(0);
            }
        }
    }

    private void handleData(Socket socket) {
        List<String> dataList = new ArrayList<>();
        dataList.add(loginField.getText());
        dataList.add(passwordField.getText());
        dataList.add(emailField.getText());
        dataList.add("Driver");
        SendRegisterData sendRegisterData = new SendRegisterData(new DriverBuilder(), dataList);
        User user = sendRegisterData.build();
        dataList.clear();
        sendDriverData(socket, user);
    }

    private void sendDriverData(Socket socket, User user) {
        try (OutputStream outputStream = socket.getOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(user);
            oos.flush();
            receiveRegisterDriverFeedback(socket);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void receiveRegisterDriverFeedback(Socket socket) throws Exception {
        InputStream inputStream = socket.getInputStream();
        int receivedBytesSize = inputStream.read();
        byte[] receivedBytes = new byte[receivedBytesSize];
        Object receivedObject = new ObjectInputStream(new ByteArrayInputStream(receivedBytes, 0, inputStream.read(receivedBytes))).readObject();
        List<String> data = new ArrayList<>((List<String>) receivedObject);
        if (data.get(0).equals("SuccessfullRegister")) {
            AlertDialog.getInstance().setParametersAndShow("Pomyślnie utworzono konto kierowcy!", Alert.AlertType.INFORMATION);
            nameField.setText("");
            emailField.setText("");
            passwordField.setText("");
            phoneNumberField.setText("");
            loginField.setText("");
        }
        else if (data.get(0).equals("AccountAlreadyExists"))
            AlertDialog.getInstance().setParametersAndShow("Użytkownik o podanym loginie już istnieje!", Alert.AlertType.ERROR);
        else AlertDialog.getInstance().setParametersAndShow("Wystąpił błąd!", Alert.AlertType.ERROR);
    }
    @Override
    public AnchorPane getButtonsAnchorPane() {
        return this.buttonsAnchorPane;
    }

}
