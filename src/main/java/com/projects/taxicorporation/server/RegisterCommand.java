package com.projects.taxicorporation.server;

import java.sql.*;
import java.util.List;
/**
 * Wzorzec projektowy polecenie (command)
 */
public class RegisterCommand implements Command {
    @Override
    public boolean execute(List<String> data, Connection connect) {
        if(checkIfAccountExists(data, connect))
            return false;
        else {
            try {
                String query = "INSERT INTO users (id_user, username, password, email, name, id_user_role) " +
                        "VALUES(users_seq.nextval, ?, ?, ?, ?, 6)";
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
    private boolean checkIfAccountExists(List<String> data, Connection connect) {
        try {
            String query = "SELECT 1 FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, data.get(0));
            ResultSet result = preparedStatement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
