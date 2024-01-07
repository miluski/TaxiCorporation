package com.projects.taxicorporation.server;

import java.net.Socket;

public class AssignManagerDepartmentTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new AssignManagerDepartmentTask(socket);
    }
}
