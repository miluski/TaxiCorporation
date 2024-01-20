package com.projects.taxicorporation.client;

import com.projects.taxicorporation.server.Command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDataBase {
    public Connection connect;
    protected Object sendRequest(String command, List<String> data) {
        return null;
    }
    public abstract void connect() throws SQLException, ClassNotFoundException;
    public abstract void closeConnect() throws SQLException ;
}
