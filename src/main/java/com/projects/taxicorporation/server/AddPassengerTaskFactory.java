package com.projects.taxicorporation.server;
import java.net.Socket;

public class AddPassengerTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new AddPassengerTask(socket);
    }
}
