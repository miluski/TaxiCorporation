package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignDriverDepartmentCommand implements Command<String> {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> informationData = new ArrayList<>();
        try {
            String query = "UPDATE users SET department = ?, city = ?, street = ? WHERE id_user = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, data.get(0));
            preparedStatement.setString(2, data.get(1));
            preparedStatement.setString(3, data.get(2));
            preparedStatement.setInt(4, Integer.parseInt(data.get(3)));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                informationData.add("AssignDriverDepartmentSuccessfull");
            else
                informationData.add("AssignDriverDepartmentUnsuccessfull");
        }
        catch (SQLException e) {
            informationData.add("AssignDriverDepartmentUnsuccessfull");
            System.out.println(e.getMessage());
        }
        return informationData;
    }
}
