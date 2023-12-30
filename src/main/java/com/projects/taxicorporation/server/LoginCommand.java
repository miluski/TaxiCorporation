package com.projects.taxicorporation.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Wzorzec projektowy polecenie (command)
 */
public class LoginCommand implements Command {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> selectedData = new ArrayList<>();
        try {
            String query = "SELECT users.username, users.email, users.department, users.city, users.street, user_roles.name " +
                    "FROM users " +
                    "LEFT JOIN user_roles ON users.id_user_role = user_roles.id_user_role " +
                    "WHERE username = ? AND password = ? ";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, data.get(0));
            preparedStatement.setString(2, data.get(1));
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                selectedData.add(result.getString(1));
                selectedData.add(result.getString(2));
                selectedData.add(result.getString(3));
                selectedData.add(result.getString(4));
                selectedData.add(result.getString(5));
                selectedData.add(result.getString(6));
            }
        }
        catch (SQLException e) {
            selectedData.add("LoginFailed");
            System.out.println(e.getMessage());
        }
        return selectedData;
    }
}
