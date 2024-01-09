package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAvailableCoursesCommand implements Command {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> availableRoutes = new ArrayList<>();
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

            String query2 = "SELECT courses.id_course, departures.hour AS departure_date, " +
                    "(SELECT addresses.street FROM addresses WHERE addresses.id_address = departures.id_address) AS departure_name, " +
                    "(SELECT addresses.street FROM addresses WHERE addresses.id_address = arrivals.id_address) AS arrival_name " +
                    "FROM courses " +
                    "JOIN departures ON courses.id_departure = departures.id_departure " +
                    "JOIN arrivals ON courses.id_arrival = arrivals.id_arrival " +
                    "WHERE (SELECT addresses.street FROM addresses WHERE addresses.id_address = departures.id_address) = '" + data.get(0) + "' " +
                    "AND (SELECT addresses.street FROM addresses WHERE addresses.id_address = arrivals.id_address) = '" + data.get(1) + "'";

            System.out.println(query2);

            while (result.next()) {
                String departureName = result.getString("departure_name");
                String arrivalName = result.getString("arrival_name");
                int courseId = result.getInt("id_course");

                RouteInfo routeInfo = new RouteInfo(departureName, arrivalName, courseId);

                availableRoutes.add(routeInfo.toString());

            }
        } catch (SQLException e) {
            availableRoutes.add("ErrorFetchingRoutes");
            System.out.println(e.getMessage());
        }

        return availableRoutes;
    }

}

class RouteInfo {
    private String departureName;
    private String arrivalName;
    private int courseId;

    public RouteInfo(String departureName, String arrivalName, int courseId) {
        this.departureName = departureName;
        this.arrivalName = arrivalName;
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Departure: " + departureName + ", Arrival: " + arrivalName + ", Course ID: " + courseId;
    }
}
