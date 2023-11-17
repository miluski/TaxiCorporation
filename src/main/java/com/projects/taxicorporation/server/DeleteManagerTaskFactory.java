package com.projects.taxicorporation.server;

import java.net.Socket;

public class DeleteManagerTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new DeleteManagerTask(socket);
    }
}
