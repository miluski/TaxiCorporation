package com.projects.taxicorporation.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteManagerCommand implements Command {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> informationData = new ArrayList<>();
        try {
            String query = "DELETE FROM USERS WHERE id_user = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(data.get(0)));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                informationData.add("DeleteManagerSuccessfull");
            else
                informationData.add("DeleteManagerUnSuccessfull");
        }
        catch (SQLException e) {
            informationData.add("DeleteManagerUnSuccessfull");
            System.out.println(e.getMessage());
        }
        return informationData;
    }
}
