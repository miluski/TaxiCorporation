package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAvailableRoutesCommand implements Command {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> availableRoutes = new ArrayList<>();

        try {
            String query = "SELECT route_name FROM routes";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                availableRoutes.add(result.getString("route_name"));
            }
        } catch (SQLException e) {
            availableRoutes.add("ErrorFetchingRoutes");
            System.out.println(e.getMessage());
        }

        return availableRoutes;
    }
}

