package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogoutCommand implements Command<String> {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> diagnosticLogoutData = new ArrayList<>();
        try {
            String query = "UPDATE users SET loggedIn = ? WHERE username = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, Boolean.toString(false));
            preparedStatement.setString(2, data.get(0));
            ResultSet result = preparedStatement.executeQuery();
            if(result.next())
                diagnosticLogoutData.add("SuccessfullLogout");
            else
                diagnosticLogoutData.add("UnsuccessfullLogout");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            diagnosticLogoutData.add("UnsuccessfullLogout");
        }
        return diagnosticLogoutData;
    }
}
