package com.projects.taxicorporation.client;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class LoginFormController implements Controller {
    private final Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$");
    @FXML
    public TextField loginTextField;
    @FXML
    public AnchorPane buttonsAnchorPane;
    @FXML
    public PasswordField passwordTextField;

    public void onRegisterButtonClicked() throws Exception {
        FormFactory formFactory = new RegisterFormFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onLoginButtonClicked() {
        if (validateData())
            communicateWithServer();
    }

    public boolean validateData() {
        if (loginTextField.getText().isEmpty()) {
            AlertDialog.getInstance().setParametersAndShow("Nie wprowadzono loginu!", AlertType.ERROR);
            return false;
        }
        if (passwordTextField.getText().isEmpty()) {
            AlertDialog.getInstance().setParametersAndShow("Nie wprowadzono hasła!", AlertType.ERROR);
            return false;
        } else if (!validatePassword()) {
            AlertDialog.getInstance().setParametersAndShow("Wprowadzone hasło jest nieprawidłowe!", AlertType.ERROR);
            return false;
        }
        return true;
    }

    public boolean validatePassword() {
        String password = passwordTextField.getText();
        return password.length() >= 9 && passPattern.matcher(password).matches();
    }

    public void communicateWithServer() {
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
        InputStream inputStream = socket.getInputStream();
        int receivedBytesSize = inputStream.read();
        byte[] receivedBytes = new byte[receivedBytesSize];
        Object receivedObject = new ObjectInputStream(new ByteArrayInputStream(receivedBytes, 0, inputStream.read(receivedBytes))).readObject();
        List<String> data = new ArrayList<>((List<String>) receivedObject);

        if (data.isEmpty())
            AlertDialog.getInstance().setParametersAndShow("Wprowadzono nieprawidłowe dane!", AlertType.ERROR);
        else if (!data.get(0).equals("LoginFailed")) {
            if (!Objects.equals(data.get(6), "true")) {
                AlertDialog.getInstance().setParametersAndShow("Pomyślnie zalogowano!", AlertType.INFORMATION);
                setUserDetails(data.get(7), data.get(0));
                redirectUser(data.get(5));
            } else
                AlertDialog.getInstance().setParametersAndShow("Na podane konto ktoś jest już zalogowany!", AlertType.ERROR);
        } else if (data.get(0).equals("DatabaseConnectError"))
            AlertDialog.getInstance().setParametersAndShow("Wystąpił nieoczekiwany błąd!", AlertType.ERROR);
    }

    private void setUserDetails(String userId, String username) {
        MainStage.getInstance().getUser().userId = userId;
        MainStage.getInstance().getUser().userName = username;
    }

    private void redirectUser(String userRank) throws Exception {
        FormFactory formFactory = null;
        switch (userRank) {
            case "Dyrektor naczelny" -> formFactory = new AddManagerFactory();
            case "Menadzer" -> formFactory = new AddDriverFactory();
            case "Pracownik techniczny", "Mechanik" -> formFactory = new ChooseRoadFactory();
            case "Kierowca" -> formFactory = new ChooseRoadFactory();
            case "Klient" -> formFactory = new ClientPanelFactory();
        }
        assert formFactory != null;
        Form form = formFactory.createForm();
        form.start();
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
        FormFactory formFactory = new LoginFormFactory();
        Form form = formFactory.createForm();
        form.start();
    }
}
