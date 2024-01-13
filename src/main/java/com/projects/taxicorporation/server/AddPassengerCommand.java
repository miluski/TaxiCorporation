package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddPassengerCommand implements Command<String> {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> informationData = new ArrayList<>();
        try {
            if (!passengerExists(connect, Integer.parseInt(data.get(0)), Integer.parseInt(data.get(1)))) {
                String query = "INSERT INTO passengers (id_passenger, id_course, id_user) VALUES (passengers_seq.nextval, ?, ?)";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setInt(1, Integer.parseInt(data.get(0)));
                preparedStatement.setInt(2, Integer.parseInt(data.get(1)));
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    informationData.add("AddPassengerSuccessfull");
                } else {
                    informationData.add("AddPassengerUnsuccessfull");
                }
            } else {
                informationData.add("AddPassengerUnsuccessfull");
            }
        } catch (SQLException e) {
            informationData.add("AddPassengerUnsuccessfull");
            System.out.println(e.getMessage());
        }
        return informationData;
    }

    private boolean passengerExists(Connection connect, int courseId, int userId) throws SQLException {
        String query = "SELECT * FROM passengers WHERE id_course = ? AND id_user = ?";
        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}
