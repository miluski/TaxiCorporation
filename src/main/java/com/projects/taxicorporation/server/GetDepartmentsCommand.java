package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetDepartmentsCommand implements Command {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> informationData = new ArrayList<>();
        try {
            String query = "SELECT departments.name, addresses.city FROM departments LEFT JOIN addresses ON " +
                    "departments.id_address = addresses.id_address";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            informationData.add("FetchedDepartments");
            if(!resultSet.next()) {
                informationData.add("FetchingDepartmentsDataError");
            }
            while(resultSet.next()) {
                informationData.add(resultSet.getString(1));
                informationData.add(resultSet.getString(2));
            }
        }
        catch (SQLException e) {
            informationData.add("FetchingDepartmentsDataError");
            System.out.println(e.getMessage());
        }
        return informationData;
    }
}
