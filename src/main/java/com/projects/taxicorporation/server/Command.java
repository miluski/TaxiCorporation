package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.util.List;

/**
 * Wzorzec projektowy polecenie (command)
 */
public interface Command {
    boolean execute(List<String> data, Connection connect);
}
