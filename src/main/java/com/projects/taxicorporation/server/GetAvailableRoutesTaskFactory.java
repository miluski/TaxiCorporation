package com.projects.taxicorporation.server;

import java.net.Socket;

public class GetAvailableRoutesTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new GetAvailableRoutesTask(socket);
    }
}
