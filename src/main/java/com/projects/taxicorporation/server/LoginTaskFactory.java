package com.projects.taxicorporation.server;

import java.net.Socket;

/**
 * Wzorzec projektowy fabryka abstrakcyjna
 */
public class LoginTaskFactory implements TaskFactory {
    /**
     * Wzorzec projektowy metoda wytwórcza
     */
    @Override
    public Task createTask(Socket socket) {
        return new LoginTask(socket);
    }
}
