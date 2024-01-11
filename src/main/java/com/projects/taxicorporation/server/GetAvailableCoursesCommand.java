package com.projects.taxicorporation.server;

import com.projects.taxicorporation.models.RouteInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAvailableCoursesCommand implements Command<RouteInfo> {
    @Override
    public List<RouteInfo> execute(List<String> data, Connection connect) {
        List<RouteInfo> availableRoutes = new ArrayList<>();
        System.out.println("data.get(0): " + data.get(0));
        System.out.println("data.get(1): " + data.get(1));
        try {
            String query = "SELECT courses.id_course, departures.hour AS departure_date, " +
                    "(SELECT addresses.street FROM addresses WHERE addresses.id_address = departures.id_address) AS departure_name, " +
                    "(SELECT addresses.street FROM addresses WHERE addresses.id_address = arrivals.id_address) AS arrival_name " +
                    "FROM courses " +
                    "JOIN departures ON courses.id_departure = departures.id_departure " +
                    "JOIN arrivals ON courses.id_arrival = arrivals.id_arrival " +
                    "WHERE (SELECT addresses.street FROM addresses WHERE addresses.id_address = departures.id_address) = ? " +
                    "AND (SELECT addresses.street FROM addresses WHERE addresses.id_address = arrivals.id_address) = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, data.get(0)); // startPoint
            preparedStatement.setString(2, data.get(1)); // destinationPoint
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                String departureName = result.getString("departure_name");
                String arrivalName = result.getString("arrival_name");
                int courseId = result.getInt("id_course");

                RouteInfo routeInfo = new RouteInfo(departureName, arrivalName, courseId);

                availableRoutes.add(routeInfo);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return availableRoutes;
    }
}