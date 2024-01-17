package com.projects.taxicorporation.server;

import java.net.Socket;

public class GetAvailableReservationsTaskFactory implements TaskFactory {
    @Override
    public Task createTask(Socket socket) {
        return new GetAvailableReservationsTask(socket);
    }
}
