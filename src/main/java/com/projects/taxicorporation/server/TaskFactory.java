package com.projects.taxicorporation.server;

import java.net.Socket;

/**
 * Wzorzec projektowy fabryka abstrakcyjna
 */
public interface TaskFactory {
    Task createTask(Socket socket);
}
