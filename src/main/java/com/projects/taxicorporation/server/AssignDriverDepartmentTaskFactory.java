package com.projects.taxicorporation.server;

import java.net.Socket;

public class AssignDriverDepartmentTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new AssignDriverDepartmentTask(socket);
    }
}
