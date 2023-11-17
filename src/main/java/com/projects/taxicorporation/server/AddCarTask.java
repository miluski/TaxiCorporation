package com.projects.taxicorporation.server;

import java.net.Socket;
import java.util.List;

public class AddCarTask extends Task {
    private final Socket clientSocket;
    private List<String> data;
    public AddCarTask(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public void sendRequest() {

    }
    @Override
    public void returnFeedback(List<String> retrievedData) {

    }
}
