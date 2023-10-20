package com.projects.taxicorporation.server;

import java.net.Socket;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;

public class LoginTask extends Task implements Runnable {
    private final Socket clientSocket;
    private List<String> data;
    public LoginTask(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
        try {
            InputStream socketReceive = clientSocket.getInputStream();
            byte[] receivedBytes = new byte[100];
            Object receivedObject = new ObjectInputStream(new ByteArrayInputStream(receivedBytes, 0, socketReceive.read(receivedBytes))).readObject();
            data = new ArrayList<>((List<String>) receivedObject);
            sendRequest();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void sendRequest() {
        try {
            DataBase dataBase = new DataBase(data);
            boolean isLoggedIn = dataBase.validateLoginData();
            dataBase.closeConnect();
            if (isLoggedIn)
                returnFeedback("Success");
            else
                returnFeedback("Error");
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            returnFeedback("Error2");
        }
    }
    @Override
    public void returnFeedback(String content) {
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            byte[] messageByteArray = content.getBytes(StandardCharsets.UTF_8);
            outputStream.write(messageByteArray);
            outputStream.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
