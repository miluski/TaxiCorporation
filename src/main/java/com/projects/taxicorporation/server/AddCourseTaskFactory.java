package com.projects.taxicorporation.server;

import java.net.Socket;

public class AddCourseTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new AddCourseTask(socket);
    }
}
