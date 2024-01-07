package com.projects.taxicorporation.server;

import java.net.Socket;

public class GetDepartmentsTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new GetDepartmentsTask(socket);
    }
}
