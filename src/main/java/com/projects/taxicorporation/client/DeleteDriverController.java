package com.projects.taxicorporation.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DeleteDriverController implements Controller {
    @FXML
    private AnchorPane buttonsAnchorPane;
    @FXML
    private ChoiceBox<String> chooseDriverChoiceBox;
    private List<String> numberOfDriverIds;
    public void onAddDriverButtonClicked() throws Exception {
        FormFactory formFactory = new AddDriverFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onMenageDriversButtonClicked() throws Exception {
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
    public void onChangeThemeButtonClicked() throws Exception {
        if (Objects.equals(MainStage.getInstance().getThemeName(), "Light")) {
            new DarkTheme().applyTheme();
        } else {
            new LightTheme().applyTheme();
        }
        FormFactory formFactory = new DeleteDriverFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onEndHireDriverButtonClicked() {
        communicateWithServer("DeleteDriver");
    }
    protected void fetchDriversData() {
        communicateWithServer("GetDrivers");
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
        if (chooseDriverChoiceBox.getValue() != null) {
            dataList.add(numberOfDriverIds.get(chooseDriverChoiceBox.getSelectionModel().getSelectedIndex()));
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
                case "DeleteDriverSuccessfull" -> {
                    AlertDialog.getInstance().setParametersAndShow("Pomyślnie usunięto wskazane konto kierowcy!", Alert.AlertType.INFORMATION);
                    chooseDriverChoiceBox.getItems().removeAll();
                    fetchDriversData();
                }
                case "FetchedDrivers" -> {
                    data.remove(0);
                    if (data.isEmpty()) data.add("Brak kont kierowców");
                    List<String> filteredData = data.stream()
                            .filter(item -> !item.matches("\\d+"))
                            .collect(Collectors.toList());
                    numberOfDriverIds = data.stream()
                            .filter(item -> item.matches("\\d+"))
                            .collect(Collectors.toList());
                    ObservableList<String> observableList = FXCollections.observableArrayList(filteredData);
                    chooseDriverChoiceBox.setItems(observableList);
                    chooseDriverChoiceBox.getSelectionModel().select(0);
                }
                case "DeleteDriverUnSuccessfull" ->
                        AlertDialog.getInstance().setParametersAndShow("Wystąpił błąd przy usuwania wskazanego konta kierowcy!", Alert.AlertType.ERROR);
            }
        }
    }
    @Override
    public AnchorPane getButtonsAnchorPane() {
        return this.buttonsAnchorPane;
    }

}
