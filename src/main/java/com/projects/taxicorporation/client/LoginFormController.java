package com.projects.taxicorporation.client;

import java.util.*;
import java.io.*;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import java.nio.charset.StandardCharsets;
import java.net.Socket;
import java.util.regex.Pattern;

public class LoginFormController {
    @FXML
    public TextField loginTextField;
    @FXML
    private PasswordField passwordTextField;
    private final Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$");
    public void onRegisterButtonClicked() throws Exception {
        FormFactory formFactory = new RegisterFormFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLoginButtonClicked() throws Exception {
        if(validateData())
            communicateWithServer();
        FormFactory formFactory = new ClientPanelFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    private boolean validateData() {
        if(loginTextField.getText().isEmpty()) {
            AlertDialog.getInstance().setParametersAndShow("Nie wprowadzono loginu!", AlertType.ERROR);
            return false;
        }
        if(passwordTextField.getText().isEmpty()) {
            AlertDialog.getInstance().setParametersAndShow("Nie wprowadzono hasła!", AlertType.ERROR);
            return false;
        }
        else if(!validatePassword()) {
            AlertDialog.getInstance().setParametersAndShow("Wprowadzone hasło jest nieprawidłowe!", AlertType.ERROR);
            return false;
        }
        return true;
    }
    private boolean validatePassword() {
        String password = passwordTextField.getText();
        return password.length() >= 9 && passPattern.matcher(password).matches();
    }
    private void communicateWithServer() {
        try(Socket socket = new Socket("localhost", 1523)) {
            sendOperationName(socket);
            sendData(socket);
            receiveFeedback(socket);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void sendOperationName(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        String operation = "LoginTask";
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
        dataList.add(loginTextField.getText());
        dataList.add(passwordTextField.getText());
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
        FormFactory formFactory = null;
        InputStream inputStream = socket.getInputStream();
        int receivedBytesSize = inputStream.read();
        byte[] receivedBytes = new byte[receivedBytesSize];
        Object receivedObject = new ObjectInputStream(new ByteArrayInputStream(receivedBytes, 0, inputStream.read(receivedBytes))).readObject();
        List<String> data = new ArrayList<>((List<String>) receivedObject);
        if(data.isEmpty())
            AlertDialog.getInstance().setParametersAndShow("Wprowadzono nieprawidłowe dane!", AlertType.ERROR);
        else if(!data.get(0).equals("LoginFailed")) {
            AlertDialog.getInstance().setParametersAndShow("Pomyślnie zalogowano!", AlertType.INFORMATION);
            switch(data.get(5)) {
                case "Dyrektor naczelny" -> formFactory = new AddManagerFactory();
                case "Menadżer", "Pracownik techniczny", "Mechanik" -> AlertDialog.getInstance().setParametersAndShow("Opcja niezaimplementowana!", AlertType.WARNING);
                case "Kierowca" -> formFactory = new DriverPanelFactory();
                case "Klient" -> formFactory = new ClientPanelFactory();
            }
            assert formFactory != null;
            Form form = formFactory.createForm();
            form.start();
        }
        else if(data.get(0).equals("DatabaseConnectError"))
            AlertDialog.getInstance().setParametersAndShow("Wystąpił nieoczekiwany błąd!", AlertType.ERROR);
    }
}
