package com.projects.taxicorporation.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class LogoutTask extends Task implements Runnable {
    private final Socket clientSocket;
    private List<String> data;
    public LogoutTask(Socket clientSocket) {
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
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void sendRequest() {
        AbstractDataBase abstractDataBase = new DataBase();
        Command logoutCommand = new LogoutCommand();
        ConcreteCommunicateMediator concreteCommunicateMediator = new ConcreteCommunicateMediator(logoutCommand, abstractDataBase);
        List<String> databaseLogoutRequestFeedback = new ArrayList<>(concreteCommunicateMediator.mediate(data));
        returnFeedback(databaseLogoutRequestFeedback);
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
