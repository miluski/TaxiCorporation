package com.projects.taxicorporation.server;

import java.net.Socket;

public class DeleteDriverTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new DeleteDriverTask(socket);
    }
}
