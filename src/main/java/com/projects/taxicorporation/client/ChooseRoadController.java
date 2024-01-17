package com.projects.taxicorporation.client;

import com.projects.taxicorporation.models.RouteInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

    private String selectedCourseID;
    private String startPoint;
    private String destinationPoint;

    public void setStartAndDestinationPoints(String startPoint, String destinationPoint) {
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
        communicateWithServer("GetAvailableCourses");
    }

    @FXML
    private AnchorPane buttonsAnchorPane;

    @FXML
    public ListView<RouteInfo> courseListView;
    public void onMapButtonClicked() throws Exception {
        FormFactory formFactory = new ShowDriverMapFactory(selectedCourseID, startPoint, destinationPoint);
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLogoutButtonClicked() {
        UserOperations userOperations = new UserOperations();
        UserFacade userFacade = new UserFacade(MainStage.getInstance().getUser(), userOperations);
        userFacade.logOutUser();
    }

    public void onChooseCourseClicked() throws Exception {
        FormFactory formFactory = new ShowDriverMapFactory(selectedCourseID, startPoint, destinationPoint);
        Form form = formFactory.createForm();
        form.start();
    }

    public void onSubmitCourseClicked(MouseEvent mouseEvent) {
    }

    public void communicateWithServer(String operationName) {
        try (Socket socket = new Socket("localhost", 1523)) {
            sendOperationName(socket, operationName);
            sendData(socket);
            receiveFeedback(socket);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void receiveFeedback(Socket socket) throws Exception {
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(inputStream);
        List<RouteInfo> data = (List<RouteInfo>) ois.readObject();

        courseListView.setCellFactory(new Callback<ListView<RouteInfo>, ListCell<RouteInfo>>() {
            @Override
            public ListCell<RouteInfo> call(ListView<RouteInfo> param) {
                return new ListCell<RouteInfo>() {
                    @Override
                    protected void updateItem(RouteInfo item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !empty) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime dateTime = LocalDateTime.parse(item.departureDate, formatter);
                            String formattedTime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                            setText("Wyjazd: " + item.departureName + " | Przyjazd: " + item.arrivalName  + " | Godzina: " + formattedTime);
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

    @FXML
    private void onCourseClicked(MouseEvent event) {
        RouteInfo selectedCourse = courseListView.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            selectedCourseID = String.valueOf(selectedCourse.courseId);
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
        dataList.add(startPoint);
        dataList.add(destinationPoint);

        oos.writeObject(dataList);
        oos.flush();

        byte[] dataByteArray = bos.toByteArray();

        outputStream.write(ByteBuffer.allocate(4).putInt(dataByteArray.length).array());
        outputStream.write(dataByteArray);
        outputStream.flush();
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
        FormFactory formFactory = new ChooseRoadFactory(startPoint, destinationPoint);
        Form form = formFactory.createForm();
        form.start();
    }
}
