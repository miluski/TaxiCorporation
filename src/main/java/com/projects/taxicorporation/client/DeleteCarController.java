package com.projects.taxicorporation.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DeleteCarController implements Controller {
    @FXML
    public ChoiceBox<String> chooseCarChoiceBox;
    @FXML
    public AnchorPane buttonsAnchorPane;
    public List<String> numberOfCarsIds;
    public void onAddCarButtonClicked() throws Exception {
        FormFactory formFactory = new AddCarFactory();
        Form form = formFactory.createForm();
        form.start();
    }
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
    public void onLogoutButtonClicked() {
        UserOperations userOperations = new UserOperations();
        UserFacade userFacade = new UserFacade(MainStage.getInstance().getUser(), userOperations);
        userFacade.logOutUser();
    }
    public void onEndDeleteCarButtonClicked() {
        boolean isDataValid = chooseCarChoiceBox.getValue() != null;
        if(isDataValid) {
            communicateWithServer("DeleteCar");
        }
        else
            AlertDialog.getInstance().setParametersAndShow("Nie wybrano samochodu!", Alert.AlertType.ERROR);
    }
    public void onChangeThemeButtonClicked() throws Exception {
        if (Objects.equals(MainStage.getInstance().getThemeName(), "Light")) {
            new DarkTheme().applyTheme();
        } else {
            new LightTheme().applyTheme();
        }
        FormFactory formFactory = new DeleteCarFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    protected void fetchCarsData() {
        communicateWithServer("GetCars");
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
        if (chooseCarChoiceBox.getValue() != null) {
            dataList.add(numberOfCarsIds.get(chooseCarChoiceBox.getSelectionModel().getSelectedIndex()));
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

    public void receiveFeedback(Socket socket) throws Exception {
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
                case "DeleteCarSuccessfull" -> {
                    AlertDialog.getInstance().setParametersAndShow("Pomyślnie usunięto wskazane auto!", Alert.AlertType.INFORMATION);
                    chooseCarChoiceBox.getItems().removeAll();
                    fetchCarsData();
                }
                case "FetchedCars" -> {
                    data.remove(0);
                    if (data.isEmpty()) data.add("Brak samochodów");
                    List<String> filteredData = data.stream()
                            .filter(item -> !item.matches("\\d+"))
                            .collect(Collectors.toList());
                    numberOfCarsIds = data.stream()
                            .filter(item -> item.matches("\\d+"))
                            .collect(Collectors.toList());
                    ObservableList<String> observableList = FXCollections.observableArrayList(filteredData);
                    chooseCarChoiceBox.setItems(observableList);
                    chooseCarChoiceBox.getSelectionModel().select(0);
                }
                case "DeleteCarUnsuccessfull" ->
                        AlertDialog.getInstance().setParametersAndShow("Wystąpił błąd przy usuwania wskazanego auta!", Alert.AlertType.ERROR);
            }
        }
    }

    @Override
    public AnchorPane getButtonsAnchorPane() {
        return this.buttonsAnchorPane;
    }

}
