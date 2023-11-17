package com.projects.taxicorporation.server;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.net.Socket;

public class RegisterTask extends Task implements Runnable {
    private final Socket clientSocket;
    private List<String> data;
    public RegisterTask(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
        try {
            InputStream socketReceive = clientSocket.getInputStream();
            int receivedBytesSize = socketReceive.read();
            byte[] receivedBytes = new byte[receivedBytesSize];
            Object receivedObject = new ObjectInputStream(new ByteArrayInputStream(receivedBytes, 0, socketReceive.read(receivedBytes))).readObject();
            data = new ArrayList<>((List<String>) receivedObject);
            sendRequest();
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void sendRequest() {
        List<String> databaseLoginRequestFeedback = new ArrayList<>();
        try {
            DataBase dataBase = new DataBase(data);
            databaseLoginRequestFeedback.addAll(dataBase.execute(new RegisterCommand()));
            dataBase.closeConnect();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            databaseLoginRequestFeedback.add("DatabaseConnectError");
        }
        finally {
            returnFeedback(databaseLoginRequestFeedback);
        }
    }
    @Override
    public void returnFeedback(List<String> retrievedData) {
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(retrievedData);
            retrievedData.clear();
            byte[] dataByteArray = bos.toByteArray();
            int dataByteArrayLength = dataByteArray.length;
            outputStream.write(dataByteArrayLength);
            outputStream.flush();
            outputStream.write(dataByteArray);
            outputStream.flush();
            oos.reset();
            bos.reset();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
