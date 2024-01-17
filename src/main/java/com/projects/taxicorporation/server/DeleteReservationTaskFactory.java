package com.projects.taxicorporation.server;

import java.net.Socket;

public class DeleteReservationTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new DeleteReservationTask(socket);
    }
}
