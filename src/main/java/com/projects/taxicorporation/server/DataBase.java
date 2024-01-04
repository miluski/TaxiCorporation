package com.projects.taxicorporation.server;

import java.sql.*;

public class DataBase extends AbstractDataBase {
    public void connect () throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.43.148:1521:xe", "system", "admin");
    }
    public void closeConnect() throws SQLException {
        connect.close();
    }
}