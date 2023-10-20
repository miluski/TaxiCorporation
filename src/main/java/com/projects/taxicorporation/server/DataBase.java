package com.projects.taxicorporation.server;

import java.sql.*;
import java.util.*;

public class DataBase {
    private final Connection connect;
    private List<String> data;
    public DataBase(List<String> data) throws SQLException, ClassNotFoundException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
        this.data = new ArrayList<>(data);
    }
    protected boolean validateLoginData() {
        try {
            String query = "SELECT 1 FROM users WHERE username = ? and password = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, data.get(0));
            preparedStatement.setString(2, data.get(1));
            ResultSet result = preparedStatement.executeQuery();
            return result.next();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    protected boolean isRegisterSuccessfull() {
        if (!validateLoginData()) {
            try {
                String query = "INSERT INTO users (id_user, username, password, email, name, id_user_role) " +
                        "VALUES(users_seq.nextval, ?, ?, ?, ?, 1)";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, data.get(0));
                preparedStatement.setString(2, data.get(1));
                preparedStatement.setString(3, data.get(2));
                preparedStatement.setString(4, data.get(3));
                preparedStatement.execute();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
    protected void closeConnect() throws SQLException {
        connect.close();
    }
}
