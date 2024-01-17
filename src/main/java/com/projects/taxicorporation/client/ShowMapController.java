package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShowMapController implements Controller {
    public MapView mapView;
    public AnchorPane buttonsAnchorPane;
    public boolean isRouteStarted;
    public int reservationId;
    public void onFindRouteButtonClicked() throws Exception {
        if(!isRouteStarted) {
            FormFactory formFactory = new ClientPanelFactory();
            Form form = formFactory.createForm();
            form.start();
        }
        else
            AlertDialog.getInstance().setParametersAndShow("Posiadasz rozpoczętą podróż!", Alert.AlertType.WARNING);

    }
    public void onLogoutButtonClicked() {
        if(reservationId!=-1)
            communicateWithServer("DeleteReservation");
        UserOperations userOperations = new UserOperations();
        UserFacade userFacade = new UserFacade(MainStage.getInstance().getUser(), userOperations);
        userFacade.logOutUser();
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
        dataList.add(Integer.toString(reservationId));
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
        new ArrayList<>((List<String>) receivedObject);
    }

    @Override
    public AnchorPane getButtonsAnchorPane() {
        return buttonsAnchorPane;
    }

    public void onChangeThemeButtonClicked() throws Exception {
        if (Objects.equals(MainStage.getInstance().getThemeName(), "Light")) {
            new DarkTheme().applyTheme();
        } else {
            new LightTheme().applyTheme();
        }
        FormFactory formFactory = new ShowMapFactory();
        Form form = formFactory.createForm();
        form.start();
    }
}
