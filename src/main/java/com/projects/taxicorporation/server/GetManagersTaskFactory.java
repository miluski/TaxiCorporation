package com.projects.taxicorporation.server;

import java.net.Socket;

public class GetManagersTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new GetManagersTask(socket);
    }
}
