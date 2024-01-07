package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddCarCommand implements Command {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> informationData = new ArrayList<>();
        try {
            String query = "INSERT INTO cars (id_car, model, model_year) VALUES (cars_seq.nextval, ?, ?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, data.get(0));
            preparedStatement.setInt(2, Integer.parseInt(data.get(1)));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                informationData.add("AddCarSuccessfull");
            else
                informationData.add("AddCarUnsuccessfull");
        } catch (SQLException e) {
            informationData.add("AddCarUnsuccessfull");
            System.out.println(e.getMessage());
        }
        return informationData;
    }
}
