package com.projects.taxicorporation.server;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.nio.charset.StandardCharsets;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private final ExecutorService executorService;
    private Server(ExecutorService executorService) {
        this.executorService = executorService;
        try {
            this.serverSocket = new ServerSocket(1523);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            shutdown();
        }
    }
    @Override
    public void run() {
        System.out.println("Server started");
        try {
            while(true) {
                clientSocket = serverSocket.accept();
                process(clientSocket);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private synchronized void process(Socket clientSocket)  {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            byte[] messageByteArray = new byte[10];
            int count = inputStream.read(messageByteArray);
            String operation = new String(messageByteArray, 0, count, StandardCharsets.UTF_8);
            runOperation(operation);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private synchronized void runOperation(String operation) {
        switch (operation) {
            case "LoginTask" -> executorService.execute(new LoginTask(clientSocket));
            case "RegisterTask" -> executorService.execute(new RegisterTask(clientSocket));
        }
    }
    private synchronized void shutdown() {
        executorService.shutdown();
    }
    public static void main(String[] args) {
        Server server = new Server(Executors.newCachedThreadPool());
        server.run();
    }
}

