package com.projects.taxicorporation.server;

import java.sql.*;
import java.util.*;

public class DataBase {
    private final Connection connect;
    private final List<String> data;
    public DataBase(List<String> data) throws SQLException, ClassNotFoundException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "john", "abcd1234");
        this.data = new ArrayList<>(data);
    }
    protected List<String> execute(Command command) {
        return command.execute(data, connect);
    }
    protected void closeConnect() throws SQLException {
        connect.close();
    }
}
