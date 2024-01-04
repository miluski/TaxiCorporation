package com.projects.taxicorporation.client;

import javafx.scene.control.*;
import java.io.*;
import java.util.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RegisterFormController implements Controller {
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
            handleData(socket);
        }
        catch (Exception e) {
            System.out.println(e.getMessage() + "ddd");
        }
    }
    private void sendOperationName(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        String operation = "RegisterTask";
        int operationStringLength = operation.length();
        byte[] messageByteArray = operation.getBytes(StandardCharsets.UTF_8);
        outputStream.write(operationStringLength);
        outputStream.flush();
        outputStream.write(messageByteArray);
        outputStream.flush();
    }
    private void handleData(Socket socket) {
        List<String> dataList = new ArrayList<>();
        dataList.add(userNameTextField.getText());
        dataList.add(passwordTextField.getText());
        dataList.add(emailTextField.getText());
        dataList.add("Client");
        SendRegisterData sendRegisterData = new SendRegisterData(new ClientBuilder(), dataList);
        User user = sendRegisterData.build();
        dataList.clear();
        sendData(socket, user);
    }
    private void sendData(Socket socket, User user) {
        try (OutputStream outputStream = socket.getOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(user);
            oos.flush();
            receiveFeedback(socket);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void receiveFeedback(Socket socket) throws Exception {
        InputStream inputStream = socket.getInputStream();
        int receivedBytesSize = inputStream.read();
        byte[] receivedBytes = new byte[receivedBytesSize];
        Object receivedObject = new ObjectInputStream(new ByteArrayInputStream(receivedBytes, 0, inputStream.read(receivedBytes))).readObject();
        List<String> data = new ArrayList<>((List<String>) receivedObject);
        if(data.get(0).equals("SuccessfullRegister")) {
            AlertDialog.getInstance().setParametersAndShow("Pomyślnie zarejestrowano!", Alert.AlertType.INFORMATION);
            onReturnButtonClicked();
        }
        else if(data.get(0).equals("AccountAlreadyExists"))
            AlertDialog.getInstance().setParametersAndShow("Użytkownik o podanym loginie już istnieje!", Alert.AlertType.ERROR);
        else
            AlertDialog.getInstance().setParametersAndShow("Wystąpił błąd!", Alert.AlertType.ERROR);
    }

    @Override
    public AnchorPane getButtonsAnchorPane() {
        return null;
    }

    public void onChangeThemeButtonClicked() throws Exception {
        if (Objects.equals(MainStage.getInstance().getThemeName(), "Light")) {
            new DarkTheme().applyTheme();
        } else {
            new LightTheme().applyTheme();
        }
        FormFactory formFactory = new RegisterFormFactory();
        Form form = formFactory.createForm();
        form.start();
    }
}
