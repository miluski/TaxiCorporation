package com.projects.taxicorporation.client;

import javafx.scene.control.*;
import java.io.*;
import java.util.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import javafx.fxml.FXML;

public class RegisterFormController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordTextField;
    private final Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$");
    public void onReturnButtonClicked() throws Exception {
        FormFactory formFactory = new LoginFormFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onRegisterButtonClicked() {
        if(validateData())
            communicateWithServer();
    }
    private boolean validateData() {
        if(nameTextField.getText().isEmpty()) {
            AlertDialog.getInstance().setParametersAndShow("Nie wprowadzono imienia!", Alert.AlertType.ERROR);
            return false;
        }
        if(emailTextField.getText().isEmpty()) {
            AlertDialog.getInstance().setParametersAndShow("Nie wprowadzono adresu email!", Alert.AlertType.ERROR);
            return false;
        }
        if(userNameTextField.getText().isEmpty()) {
            AlertDialog.getInstance().setParametersAndShow("Nie wprowadzono loginu!", Alert.AlertType.ERROR);
            return false;
        }
        if(passwordTextField.getText().isEmpty()) {
            AlertDialog.getInstance().setParametersAndShow("Nie wprowadzono hasła!", Alert.AlertType.ERROR);
            return false;
        }
        else if(!validatePassword()) {
            AlertDialog.getInstance().setParametersAndShow("Wprowadzone hasło nie spełnia wymogów bezpieczeństwa!", Alert.AlertType.ERROR);
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
        String operation = "RegisterTask";
        byte[] messageByteArray = operation.getBytes(StandardCharsets.UTF_8);
        outputStream.write(messageByteArray);
        outputStream.flush();
    }
    private void sendData(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        List<String> dataList = new ArrayList<>();
        dataList.add(userNameTextField.getText());
        dataList.add(passwordTextField.getText());
        dataList.add(emailTextField.getText());
        dataList.add(nameTextField.getText());
        oos.writeObject(dataList);
        byte[] dataByteArray = bos.toByteArray();
        outputStream.write(dataByteArray);
        outputStream.flush();
    }
    private void receiveFeedback(Socket socket) throws Exception {
        InputStream inputStream = socket.getInputStream();
        byte[] messageByteArray = new byte[20];
        int count = inputStream.read(messageByteArray);
        String feedback = new String(messageByteArray, 0, count, StandardCharsets.UTF_8);
        if(feedback.equals("Success")) {
            AlertDialog.getInstance().setParametersAndShow("Pomyślnie zarejestrowano!", Alert.AlertType.INFORMATION);
            onReturnButtonClicked();
        }
        else
            AlertDialog.getInstance().setParametersAndShow("Wystąpił błąd!", Alert.AlertType.ERROR);
    }
}
