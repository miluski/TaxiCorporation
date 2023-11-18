package com.projects.taxicorporation.server;

import java.net.Socket;

public class AddDepartmentTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new AddDepartmentTask(socket);
    }
}
