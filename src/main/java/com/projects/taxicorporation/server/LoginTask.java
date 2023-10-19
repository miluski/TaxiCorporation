package com.projects.taxicorporation.server;

import java.net.Socket;
import java.io.*;
import java.util.*;

public class LoginTask implements Task, Runnable {
    private String userName;
    private String password;
    private Socket clientSocket;
    public LoginTask(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
        try {
            List<String> data = new ArrayList<>();
            InputStream socketReceive = clientSocket.getInputStream();
            byte[] receivedBytes = new byte[100];
            Object receivedObject = new ObjectInputStream(new ByteArrayInputStream(receivedBytes, 0, socketReceive.read(receivedBytes))).readObject();
            data.addAll((List<String>)receivedObject);
            this.userName = data.get(0);
            this.password = data.get(1);
            sendRequest();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void sendRequest() {
        //TODO połączenie z bazą danych celem wysyłu danych
    }
    @Override
    public void receiveFeedback() {
        //TODO wysłanie otrzymanych danych z bazy do serwera
    }
}
