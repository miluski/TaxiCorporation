package com.projects.taxicorporation.server;

import java.sql.*;
import java.util.List;
/**
 * Wzorzec projektowy polecenie (command)
 */
public class LoginCommand implements Command {
    @Override
    public boolean execute(List<String> data, Connection connect) {
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
}
