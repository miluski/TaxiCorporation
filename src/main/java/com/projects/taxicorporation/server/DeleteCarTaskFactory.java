package com.projects.taxicorporation.server;

import java.net.Socket;

public class DeleteCarTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new DeleteCarTask(socket);
    }
}
