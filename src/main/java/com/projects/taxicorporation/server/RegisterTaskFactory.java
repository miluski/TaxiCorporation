package com.projects.taxicorporation.server;

import java.net.Socket;

public class RegisterTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new RegisterTask(socket);
    }
}
