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
        } catch (IOException e) {
            System.out.println(e.getMessage());
            shutdown();
        }
    }

    @Override
    public void run() {
        System.out.println("Server started");
        try {
            while (true) {
                clientSocket = serverSocket.accept();
                process(clientSocket);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private synchronized void process(Socket clientSocket) {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            int messageByteArrayLength = inputStream.read();
            byte[] messageByteArray = new byte[messageByteArrayLength];
            int count = inputStream.read(messageByteArray);
            String operation = new String(messageByteArray, 0, count, StandardCharsets.UTF_8);
            runOperation(operation);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private synchronized void runOperation(String operation) {
        TaskFactory taskFactory = null;
        switch (operation) {
            case "LoginTask" -> taskFactory = new LoginTaskFactory();
            case "RegisterTask" -> taskFactory = new RegisterTaskFactory();
            case "AddCar" -> taskFactory = new AddCarTaskFactory();
            case "AddDepartment" -> taskFactory = new AddDepartmentTaskFactory();
            case "DeleteCar" -> taskFactory = new DeleteCarTaskFactory();
            case "DeleteDriver" -> taskFactory = new DeleteDriverTaskFactory();
            case "DeleteManager" -> taskFactory = new DeleteManagerTaskFactory();
            case "RenameDepartment" -> taskFactory = new RenameDepartmentTaskFactory();
            case "DeleteDepartment" -> taskFactory = new DeleteDepartmentTaskFactory();
            case "LogoutTask" -> taskFactory = new LogoutTaskFactory();
            case "GetAvailableCourses" -> taskFactory = new GetAvailableCoursesTaskFactory();
            case "GetDepartments" -> taskFactory = new GetDepartmentsTaskFactory();
            case "GetManagers" -> taskFactory = new GetManagersTaskFactory();
            case "GetCars" -> taskFactory = new GetCarsTaskFactory();
            case "GetDrivers" -> taskFactory = new GetDriversTaskFactory();
            case "AssignManagerDepartment" -> taskFactory = new AssignManagerDepartmentTaskFactory();
            case "AssignDriverDepartment" -> taskFactory = new AssignDriverDepartmentTaskFactory();
        }
        assert taskFactory != null;
        Task task = taskFactory.createTask(clientSocket);
        executorService.execute((Runnable) task);
    }

    private synchronized void shutdown() {
        executorService.shutdown();
    }

    public static void main(String[] args) {
        Server server = new Server(Executors.newCachedThreadPool());
        server.run();
    }
}

