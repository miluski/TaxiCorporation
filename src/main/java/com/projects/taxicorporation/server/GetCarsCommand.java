package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetCarsCommand implements Command {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> informationData = new ArrayList<>();
        try {
            String query = "SELECT id_car, model, model_year FROM cars";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            informationData.add("FetchedCars");
            while(resultSet.next()) {
                informationData.add(resultSet.getString(2) + ", " + resultSet.getString(3));
                informationData.add(Integer.toString(resultSet.getInt(1)));
            }
        }
        catch (SQLException e) {
            informationData.add("FetchingCarsDataError");
            System.out.println(e.getMessage());
        }
        return informationData;
    }
}
