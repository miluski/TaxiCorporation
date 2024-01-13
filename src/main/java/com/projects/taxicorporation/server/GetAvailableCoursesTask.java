package com.projects.taxicorporation.server;

import com.projects.taxicorporation.models.RouteInfo;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;


public class GetAvailableCoursesTask extends Task implements Runnable {
    private final Socket clientSocket;

    public GetAvailableCoursesTask(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("GetAvailableCoursesTask is running");
        sendRequest();
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

    @Override
    public void sendRequest() {
        try {
            InputStream socketReceive = clientSocket.getInputStream();

            byte[] lengthBytes = new byte[4];
            socketReceive.read(lengthBytes);
            int length = ByteBuffer.wrap(lengthBytes).getInt();

            byte[] dataBytes = new byte[length];
            socketReceive.read(dataBytes);

            ByteArrayInputStream bis = new ByteArrayInputStream(dataBytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            List<String> data = (List<String>) ois.readObject();

            AbstractDataBase abstractDataBase = new DataBase();
            Command getAvailableCoursesCommand = new GetAvailableCoursesCommand();
            ConcreteCommunicateMediator concreteCommunicateMediator = new ConcreteCommunicateMediator(getAvailableCoursesCommand, abstractDataBase);
            List<String> databaseResponse = new ArrayList<>(concreteCommunicateMediator.mediate(data));

            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(databaseResponse);
            oos.flush();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}

