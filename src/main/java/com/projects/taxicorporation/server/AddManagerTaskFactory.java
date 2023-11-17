package com.projects.taxicorporation.server;

import java.net.Socket;

public class AddManagerTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new AddManagerTask(socket);
    }
}
