package com.projects.taxicorporation.server;

import java.net.Socket;

public class AddCarTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new AddCarTask(socket);
    }
}
