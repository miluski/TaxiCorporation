package com.projects.taxicorporation.client;

import javafx.scene.control.Alert;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserOperations {
    private String username;

    public void logOut(String username) {
        this.username = username;
        communicateWithServer();
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
        OutputStream outputStream = socket.getOutputStream();
        String operation = "LogoutTask";
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
        dataList.add(this.username);
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
            if (data.get(0).equals("SuccessfullLogout")) {
                AlertDialog.getInstance().setParametersAndShow("Pomyślnie wylogowano!", Alert.AlertType.INFORMATION);
                MainStage.getInstance().getUser().userName = "";
                FormFactory loginFormFactory = new LoginFormFactory();
                Form loginForm = loginFormFactory.createForm();
                loginForm.start();
            } else {
                AlertDialog.getInstance().setParametersAndShow("Wystąpił nieoczekiwany błąd!", Alert.AlertType.ERROR);
            }
        }
    }
}
