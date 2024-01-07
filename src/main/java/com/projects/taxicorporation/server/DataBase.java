package com.projects.taxicorporation.server;

import java.sql.*;

public class DataBase extends AbstractDataBase {
    public void connect () throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "john", "abcd1234");
    }
    public void closeConnect() throws SQLException {
        connect.close();
    }
}