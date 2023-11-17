package com.projects.taxicorporation.server;

import java.net.Socket;

public class AddDriverTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new AddDriverTask(socket);
    }
}
