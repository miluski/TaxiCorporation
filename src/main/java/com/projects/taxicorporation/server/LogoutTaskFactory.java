package com.projects.taxicorporation.server;

import java.net.Socket;

public class LogoutTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new LogoutTask(socket);
    }
}
