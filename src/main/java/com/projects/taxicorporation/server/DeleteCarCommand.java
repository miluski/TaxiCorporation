package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteCarCommand implements Command {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> informationData = new ArrayList<>();
        try {
            String query = "DELETE FROM cars WHERE id_car = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(data.get(0)));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                informationData.add("DeleteCarSuccessfull");
            else
                informationData.add("DeleteCarUnSuccessfull");
        }
        catch (SQLException e) {
            informationData.add("DeleteCarUnSuccessfull");
            System.out.println(e.getMessage());
        }
        return informationData;
    }
}
