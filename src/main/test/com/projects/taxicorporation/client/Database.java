package com.projects.taxicorporation.client;

import java.sql.SQLException;
import java.util.List;

public class Database extends AbstractDataBase {
    List<String> data;

    @Override
    public Object sendRequest(String command, List<String> data) {
        if (command.startsWith("Add") || command.startsWith("Assign") || command.startsWith("Delete") ) {
            return true;
        }
        return data;
    }

    @Override
    public void connect() throws SQLException, ClassNotFoundException {

    }

    @Override
    public void closeConnect() throws SQLException {

    }
}