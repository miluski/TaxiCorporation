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
    private TextField loginTextField;
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
            showAlert("Nie wprowadzono loginu!", AlertType.ERROR);
            return false;
        }
        if(passwordTextField.getText().isEmpty()) {
            showAlert("Nie wprowadzono hasła!", AlertType.ERROR);
            return false;
        }
        else if(!validatePassword()) {
            showAlert("Wprowadzone hasło jest nieprawidłowe!", AlertType.ERROR);
            return false;
        }
        return true;
    }
    private boolean validatePassword() {
        String password = passwordTextField.getText();
        return password.length() >= 9 && passPattern.matcher(password).matches();
    }
    private void showAlert(String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Informacja");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    private void communicateWithServer() {
        try(Socket socket = new Socket("localhost", 1523)) {
            sendOperationName(socket);
            sendData(socket);
            receiveFeedback(socket);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private void sendOperationName(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        String operation = "LoginTask";
        byte[] messageByteArray = operation.getBytes(StandardCharsets.UTF_8);
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
        byte[] dataByteArray = bos.toByteArray();
        outputStream.write(dataByteArray);
        outputStream.flush();
    }
    private void receiveFeedback(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        byte[] messageByteArray = new byte[20];
        int count = inputStream.read(messageByteArray);
        String feedback = new String(messageByteArray, 0, count, StandardCharsets.UTF_8);
        if(feedback.equals("Success"))
            showAlert("Pomyślnie zalogowano!", AlertType.INFORMATION);
        //TODO: Po sukcesie utworzenie z pomocą fabryki abstrakcyjnej formularza (panelu) odpowiedniego użytkownikowi
        //TODO: Aktualizacja rzeczy zwracanych z bazy - trzeba zwrócić rangę
        else if(feedback.equals("Error"))
            showAlert("Wprowadzono nieprawidłowe dane!", AlertType.ERROR);
        else
            showAlert("Wystąpił nieoczekiwany błąd!", AlertType.ERROR);
    }
}
