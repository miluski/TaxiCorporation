package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteDriverCommand implements Command<String> {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> informationData = new ArrayList<>();
        try {
            String query = "DELETE FROM USERS WHERE id_user = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(data.get(0)));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                informationData.add("DeleteDriverSuccessfull");
            else
                informationData.add("DeleteDriverUnSuccessfull");
        }
        catch (SQLException e) {
            informationData.add("DeleteDriverUnSuccessfull");
            System.out.println(e.getMessage());
        }
        return informationData;
    }
}
