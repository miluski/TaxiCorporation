package com.projects.taxicorporation.server;

import com.projects.taxicorporation.models.RouteInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetAvailableReservationsCommand implements Command<RouteInfo> {
    @Override
    public List<RouteInfo> execute(List<String> data, Connection connect) {
        List<RouteInfo> availableRoutes = new ArrayList<>();
        try {
            String query = "SELECT reservations.id_reservation, departures.hour AS departure_date, " +
                    "(SELECT addresses.street FROM addresses WHERE addresses.id_address = departures.id_address) AS departure_name, " +
                    "(SELECT addresses.street FROM addresses WHERE addresses.id_address = arrivals.id_address) AS arrival_name " +
                    "FROM reservations " +
                    "JOIN departures ON reservations.id_departure = departures.id_departure " +
                    "JOIN arrivals ON reservations.id_arrival = arrivals.id_arrival ";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                String departureName = result.getString("departure_name");
                String arrivalName = result.getString("arrival_name");
                String departureDate = result.getString("departure_date");
                int reservationId = result.getInt("id_reservation");
                RouteInfo routeInfo = new RouteInfo(reservationId, departureName, arrivalName, departureDate);
                availableRoutes.add(routeInfo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return availableRoutes;
    }
}