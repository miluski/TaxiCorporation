package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDataBase {
    public Connection connect;
    protected List<String> execute(Command command, List<String> data) {
        try {
            connect();
            List<String> retrievedData = command.execute(data, connect);
            closeConnect();
            return retrievedData;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public abstract void connect() throws SQLException, ClassNotFoundException;
    public abstract void closeConnect() throws SQLException ;
}
