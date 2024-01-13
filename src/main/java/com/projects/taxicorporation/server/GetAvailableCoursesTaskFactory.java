package com.projects.taxicorporation.server;

import java.net.Socket;

public class GetAvailableCoursesTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new GetAvailableCoursesTask(socket);
    }
}
