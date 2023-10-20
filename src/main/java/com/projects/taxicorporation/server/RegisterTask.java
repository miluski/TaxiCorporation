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
            byte[] receivedBytes = new byte[100];
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
        try {
            DataBase dataBase = new DataBase(data);
            boolean isRegisterSuccessfull = dataBase.isRegisterSuccessfull();
            dataBase.closeConnect();
            if (isRegisterSuccessfull)
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
