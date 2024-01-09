package com.projects.taxicorporation.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChooseRoadController implements Controller {

    private String startPoint;
    private String destinationPoint;

    public void setStartAndDestinationPoints(String startPoint, String destinationPoint) {
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
    }

    @FXML
    private AnchorPane buttonsAnchorPane;
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

    public void onSubmitCourseClicked(MouseEvent mouseEvent) {
        communicateWithServer("GetAvailableCourses");
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

    private void receiveFeedback(Socket socket) throws Exception {
        InputStream inputStream = socket.getInputStream();
        int receivedBytesSize = inputStream.read();
        byte[] receivedBytes = new byte[receivedBytesSize];
        Object receivedObject = new ObjectInputStream(new ByteArrayInputStream(receivedBytes, 0, inputStream.read(receivedBytes))).readObject();
        List<String> data = new ArrayList<>((List<String>) receivedObject);
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
