package com.projects.taxicorporation.server;
import java.net.Socket;

public class AddReservationTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new AddReservationTask(socket);
    }
}
