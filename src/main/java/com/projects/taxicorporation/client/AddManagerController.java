package com.projects.taxicorporation.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AddManagerController implements Controller {
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
    private List<String> numberOfDepartmentStreets;

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

    public void onEndAddMenagerButtonClicked() {
        if (validateData() && !Objects.equals(departmentChoiceBox.getValue(), "Brak oddziałów")) {
            communicateWithServer("RegisterTask");
        } else
            AlertDialog.getInstance().setParametersAndShow("Wprowadzone dane są nieprawidłowe!", Alert.AlertType.ERROR);
    }

    private boolean validateData() {
        if (nameField.getText().isEmpty()) {
            AlertDialog.getInstance().setParametersAndShow("Nie wprowadzono imienia!", Alert.AlertType.ERROR);
            return false;
        }
        if (emailField.getText().isEmpty()) {
            AlertDialog.getInstance().setParametersAndShow("Nie wprowadzono adresu email!", Alert.AlertType.ERROR);
            return false;
        }
        if (loginField.getText().isEmpty()) {
            AlertDialog.getInstance().setParametersAndShow("Nie wprowadzono loginu!", Alert.AlertType.ERROR);
            return false;
        }
        if (passwordField.getText().isEmpty()) {
            AlertDialog.getInstance().setParametersAndShow("Nie wprowadzono hasła!", Alert.AlertType.ERROR);
            return false;
        }
        if (phoneNumberField.getText().isEmpty()) {
            AlertDialog.getInstance().setParametersAndShow("Nie wprowadzono numeru telefonu!", Alert.AlertType.ERROR);
            return false;
        } else if (!validatePassword()) {
            AlertDialog.getInstance().setParametersAndShow("Wprowadzone hasło nie spełnia wymogów bezpieczeństwa!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$");
        String password = passwordField.getText();
        return password.length() >= 9 && passPattern.matcher(password).matches();
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
        FormFactory formFactory = new AddManagerFactory();
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
                numberOfDepartmentStreets = data.stream().
                        filter(item -> item.startsWith("Street: ")).
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
        dataList.add("Manager");
        dataList.add(departmentChoiceBox.getValue().substring(0, departmentChoiceBox.getValue().indexOf(",")));
        dataList.add(departmentChoiceBox.getValue().substring(departmentChoiceBox.getValue().indexOf(",")+2));
        dataList.add(numberOfDepartmentStreets.get(departmentChoiceBox.getSelectionModel().getSelectedIndex()));
        SendRegisterData sendRegisterData = new SendRegisterData(new ManagerBuilder(), dataList);
        User user = sendRegisterData.build();
        dataList.clear();
        sendManagerData(socket, user);
    }

    private void sendManagerData(Socket socket, User user) {
        try (OutputStream outputStream = socket.getOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(user);
            oos.flush();
            receiveRegisterManagerFeedback(socket);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void receiveRegisterManagerFeedback(Socket socket) throws Exception {
        InputStream inputStream = socket.getInputStream();
        int receivedBytesSize = inputStream.read();
        byte[] receivedBytes = new byte[receivedBytesSize];
        Object receivedObject = new ObjectInputStream(new ByteArrayInputStream(receivedBytes, 0, inputStream.read(receivedBytes))).readObject();
        List<String> data = new ArrayList<>((List<String>) receivedObject);
        if (data.get(0).equals("SuccessfullRegister")) {
            AlertDialog.getInstance().setParametersAndShow("Pomyślnie utworzono konto menadżera!", Alert.AlertType.INFORMATION);
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
