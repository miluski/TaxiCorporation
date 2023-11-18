package com.projects.taxicorporation.server;

import java.net.Socket;

public class RenameDepartmentTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new RenameDepartmentTask(socket);
    }
}
