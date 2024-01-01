package com.projects.taxicorporation.server;

import com.projects.taxicorporation.client.*;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterTask extends Task implements Runnable {
    private final Socket clientSocket;
    private final List<String> data = new ArrayList<>();

    public RegisterTask(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
        try (InputStream inputStream = clientSocket.getInputStream();
             ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            User user = (User) ois.readObject();
            if(user instanceof ChiefExecutive chiefExecutive) {
                data.add(chiefExecutive.getUsername());
                data.add(chiefExecutive.getPassword());
                data.add(chiefExecutive.getEmail());
                data.add(chiefExecutive.getRank());
                data.add(chiefExecutive.getDepartment());
                data.add(chiefExecutive.getCity());
                data.add(chiefExecutive.getStreet());
            }
            else if(user instanceof Manager manager){
                data.add(manager.getUsername());
                data.add(manager.getPassword());
                data.add(manager.getEmail());
                data.add(manager.getRank());
                data.add(manager.getDepartment());
                data.add(manager.getCity());
                data.add(manager.getStreet());
            }
            else if(user instanceof TechnicalWorker technicalWorker){
                data.add(technicalWorker.getUsername());
                data.add(technicalWorker.getPassword());
                data.add(technicalWorker.getEmail());
                data.add(technicalWorker.getRank());
                data.add(technicalWorker.getDepartment());
                data.add("");
                data.add("");
            }
            else if(user instanceof Driver driver){
                data.add(driver.getUsername());
                data.add(driver.getPassword());
                data.add(driver.getEmail());
                data.add(driver.getRank());
                data.add("");
                data.add("");
                data.add("");
            }
            else if(user instanceof Client client){
                data.add(client.getUsername());
                data.add(client.getPassword());
                data.add(client.getEmail());
                data.add(client.getRank());
                data.add("");
                data.add("");
                data.add("");
            }
            else if(user instanceof Mechanic mechanic){
                data.add(mechanic.getUsername());
                data.add(mechanic.getPassword());
                data.add(mechanic.getEmail());
                data.add(mechanic.getRank());
                data.add(mechanic.getDepartment());
                data.add("");
                data.add("");
            }
            sendRequest();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void sendRequest() {
        AbstractDataBase abstractDataBase = new DataBase();
        Command registerCommand = new RegisterCommand();
        ConcreteCommunicateMediator concreteCommunicateMediator = new ConcreteCommunicateMediator(registerCommand, abstractDataBase);
        List<String> databaseLoginRequestFeedback = new ArrayList<>(concreteCommunicateMediator.mediate(data));
        returnFeedback(databaseLoginRequestFeedback);
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
