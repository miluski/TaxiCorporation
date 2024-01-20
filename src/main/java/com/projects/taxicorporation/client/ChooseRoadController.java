package com.projects.taxicorporation.client;

import com.projects.taxicorporation.models.RouteInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChooseRoadController implements Controller {
    @FXML
    private AnchorPane buttonsAnchorPane;
    public ListView<RouteInfo> courseListView;
    private String startPoint = "";
    private String destinationPoint = "";
    private int reservationId = -1;
    public void onMapButtonClicked() throws Exception {
        FormFactory formFactory = new ShowDriverMapFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLogoutButtonClicked() {
        UserOperations userOperations = new UserOperations();
        UserFacade userFacade = new UserFacade(MainStage.getInstance().getUser(), userOperations);
        userFacade.logOutUser();
    }

    public void onChooseCourseClicked() throws Exception {
        if(!Objects.equals(startPoint, "") && !Objects.equals(destinationPoint, "")) {
            System.out.println("before");
            communicateWithServer("DeleteReservation");
            System.out.println("after");
            FormFactory formFactory = new ShowDriverMapFactory(startPoint, destinationPoint, true);
            Form form = formFactory.createForm();
            form.start();
        }
        else
            AlertDialog.getInstance().setParametersAndShow("Zaznacz rezerwację do zaakceptowania!", Alert.AlertType.ERROR);
    }
    public void communicateWithServer(String operationName) {
        try (Socket socket = new Socket("localhost", 1523)) {
            sendOperationName(socket, operationName);
            sendData(socket, operationName);
            if(operationName.equals("GetAvailableReservations"))
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

    private void sendData(Socket socket, String operationName) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        List<String> dataList = new ArrayList<>();
        switch(operationName) {
            case "DeleteReservation" ->
                dataList.add(Integer.toString(this.reservationId));
            case "AddCourse" -> {
                dataList.add(Integer.toString(1));
                dataList.add(Integer.toString(getArrivalId()));
                dataList.add(Integer.toString(getDestinationId()));
            }
            default -> dataList.add("");
        }
        oos.writeObject(dataList);
        oos.flush();
        byte[] dataByteArray = bos.toByteArray();
        outputStream.write(ByteBuffer.allocate(4).putInt(dataByteArray.length).array());
        outputStream.write(dataByteArray);
        outputStream.flush();
    }
    public void receiveFeedback(Socket socket) throws Exception {
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(inputStream);
        List<RouteInfo> data = (List<RouteInfo>) ois.readObject();
        courseListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<RouteInfo> call(ListView<RouteInfo> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(RouteInfo item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !empty) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime dateTime = LocalDateTime.parse(item.departureDate, formatter);
                            String formattedTime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                            setText("Wyjazd: " + item.departureName + " | Przyjazd: " + item.arrivalName + " | Godzina: " + formattedTime);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
        ObservableList<RouteInfo> courseList = FXCollections.observableArrayList(data);
        courseListView.setItems(courseList);
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
        FormFactory formFactory = new ChooseRoadFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onCourseClicked() {
        RouteInfo routeInfo = courseListView.getSelectionModel().getSelectedItem();
        if(routeInfo!=null) {
            this.startPoint = routeInfo.arrivalName;
            this.destinationPoint = routeInfo.departureName;
            this.reservationId = routeInfo.reservationId;
        }
    }
    private int getArrivalId() {
        switch (this.startPoint) {
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
        switch (this.destinationPoint) {
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
