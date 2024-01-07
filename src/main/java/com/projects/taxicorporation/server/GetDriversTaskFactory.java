package com.projects.taxicorporation.server;

import java.net.Socket;

public class GetDriversTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new GetDriversTask(socket);
    }
}
