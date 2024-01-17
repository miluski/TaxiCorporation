package com.projects.taxicorporation.client;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ClientPanelController implements Controller {
    private final ConcreteLocationFlyweight location1 = new ConcreteLocationFlyweight(1, "Swietokrzyska", 50.877781, 20.661020);
    private final ConcreteLocationFlyweight location2 = new ConcreteLocationFlyweight(2, "Warszawska", 50.880063, 20.637531);
    private final ConcreteLocationFlyweight location3 = new ConcreteLocationFlyweight(3, "Sandomierska", 50.870842, 20.645495);
    private final ConcreteLocationFlyweight location4 = new ConcreteLocationFlyweight(4, "Piekoszowska", 50.875466, 20.612858);
    public TextField startPointField;
    public ChoiceBox<String> startPointChoiceBox;
    public ChoiceBox<String> destinationChoiceBox;
    public AnchorPane buttonsAnchorPane;

    public void initialize() {
        initializeLocations();
    }

    private void initializeLocations() {
        startPointChoiceBox.getItems().addAll(location1.name, location2.name, location3.name, location4.name);
        destinationChoiceBox.getItems().addAll(location1.name, location2.name, location3.name, location4.name);
        startPointChoiceBox.setValue(location1.name);
        destinationChoiceBox.setValue(location4.name);
        startPointChoiceBox.setOnAction(event -> updateDestinationChoiceBox());
        destinationChoiceBox.setOnAction(event -> updateStartPointChoiceBox());
    }

    private void updateDestinationChoiceBox() {
        String selectedStartPoint = startPointChoiceBox.getValue();
        if (selectedStartPoint != null)
            destinationChoiceBox.getItems().remove(selectedStartPoint);
    }

    private void updateStartPointChoiceBox() {
        String selectedDestination = destinationChoiceBox.getValue();
        if (selectedDestination != null)
            startPointChoiceBox.getItems().remove(selectedDestination);
    }

    public void onMapButtonClicked() throws Exception {
        FormFactory formFactory = new ShowMapFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onLogoutButtonClicked() {
        UserOperations userOperations = new UserOperations();
        UserFacade userFacade = new UserFacade(MainStage.getInstance().getUser(), userOperations);
        userFacade.logOutUser();
    }

    public void onEndReservateButtonClicked() {
        String startPoint = startPointChoiceBox.getValue();
        String destinationPoint = destinationChoiceBox.getValue();
        if (startPoint != null && destinationPoint != null) {
            try {
                communicateWithServer();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
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
        String operationName = "AddReservation";
        OutputStream outputStream = socket.getOutputStream();
        int operationStringLength = operationName.length();
        byte[] messageByteArray = operationName.getBytes(StandardCharsets.UTF_8);
        outputStream.write(operationStringLength);
        outputStream.flush();
        outputStream.write(messageByteArray);
        outputStream.flush();
    }

    private void sendData(Socket socket) throws IOException {
        User user = MainStage.getInstance().getUser();
        OutputStream outputStream = socket.getOutputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        List<String> dataList = new ArrayList<>();
        int arrivalId = getArrivalId();
        int destinationId = getDestinationId();
        dataList.add(user.userId);
        dataList.add(Integer.toString(destinationId));
        dataList.add(Integer.toString(arrivalId));
        oos.writeObject(dataList);
        byte[] dataByteArray = bos.toByteArray();
        int dataByteArrayLength = dataByteArray.length;
        outputStream.write(dataByteArrayLength);
        outputStream.flush();
        outputStream.write(dataByteArray);
        outputStream.flush();
        dataList.clear();
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
                case "AddReservationSuccessfull" -> {
                    AlertDialog.getInstance().setParametersAndShow("Pomyślnie zarezerwowano przejazd!", Alert.AlertType.INFORMATION);
                    FormFactory formFactory = new ShowMapFactory(startPointChoiceBox.getSelectionModel().getSelectedItem(), destinationChoiceBox.getSelectionModel().getSelectedItem(), true, Integer.parseInt(data.get(1)));
                    Form form = formFactory.createForm();
                    form.start();
                }
                case "AddReservationUnsuccessfull" ->
                        AlertDialog.getInstance().setParametersAndShow("Nie udało dodać się rezerwacji!", Alert.AlertType.ERROR);
            }
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
        FormFactory formFactory = new ClientPanelFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    private int getArrivalId() {
        String arrivalName = startPointChoiceBox.getSelectionModel().getSelectedItem();
        switch (arrivalName) {
            case "Swietokrzyska" -> {
                return 1;
            }
            case "Warszawska" -> {
                return 2;
            }
            case "Sandomierska" -> {
                return 3;
            }
            case "Piekoszowska" -> {
                return 4;
            }
        }
        return -1;
    }

    private int getDestinationId() {
        String destinationName = destinationChoiceBox.getSelectionModel().getSelectedItem();
        switch (destinationName) {
            case "Swietokrzyska" -> {
                return 1;
            }
            case "Warszawska" -> {
                return 2;
            }
            case "Sandomierska" -> {
                return 3;
            }
            case "Piekoszowska" -> {
                return 4;
            }
        }
        return -1;
    }
}
