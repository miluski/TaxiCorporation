package com.projects.taxicorporation.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddReservationCommand implements Command<String> {
    private Connection connect;
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        this.connect = connect;
        List<String> informationData = new ArrayList<>();
        try {
            int userId = Integer.parseInt(data.get(0));
            int arrivalId = Integer.parseInt(data.get(1));
            int departureId = Integer.parseInt(data.get(2));
            boolean isReservationExists = getIsReservationExists(userId, arrivalId, departureId);
            if (!isReservationExists) {
                String query = "INSERT INTO reservations (id_reservation, id_user, id_arrival, id_departure) VALUES (reservations_seq.nextval, ?, ?, ?)";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, arrivalId);
                preparedStatement.setInt(3, departureId);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    informationData.add("AddReservationSuccessfull");
                    informationData.add(Integer.toString(getLastReservationId()));
                } else {
                    informationData.add("AddReservationUnsuccessfull");
                }
            } else {
                informationData.add("AddReservationUnsuccessfull");
            }
        } catch (SQLException e) {
            informationData.add("AddReservationUnsuccessfull");
            System.out.println(e.getMessage());
        }
        return informationData;
    }

    private boolean getIsReservationExists(int userId, int arrivalId, int departureId) throws SQLException {
        String query = "SELECT * FROM reservations WHERE id_user = ? AND id_arrival = ? AND id_departure = ?";
        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, arrivalId);
            preparedStatement.setInt(3, departureId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private int getLastReservationId() throws SQLException {
        String query = "SELECT MAX(id_reservation) FROM reservations";
        try(Statement statement = connect.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next())
                return resultSet.getInt(1);
        }
        return -1;
    }
}
