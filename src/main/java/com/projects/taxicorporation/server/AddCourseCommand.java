package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddCourseCommand implements Command<String> {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> informationData = new ArrayList<>();
        try {
            int driverId = Integer.parseInt(data.get(0));
            int arrivalId = Integer.parseInt(data.get(1));
            int departureId = Integer.parseInt(data.get(2));
            String query = "INSERT INTO courses (id_course, id_driver, id_arrival, id_departure) VALUES (courses_seq.nextval, ?, ?, ?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, driverId);
            preparedStatement.setInt(2, arrivalId);
            preparedStatement.setInt(3, departureId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                informationData.add("AddCourseSuccessfull");
            } else {
                informationData.add("AddCourseUnsuccessfull");
            }
        } catch (SQLException e) {
            informationData.add("AddCourseUnsuccessfull");
            System.out.println(e.getMessage());
        }
        return informationData;
    }
}
