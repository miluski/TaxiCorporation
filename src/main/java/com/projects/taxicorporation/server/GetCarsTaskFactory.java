package com.projects.taxicorporation.server;

import java.net.Socket;

public class GetCarsTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new GetCarsTask(socket);
    }
}
