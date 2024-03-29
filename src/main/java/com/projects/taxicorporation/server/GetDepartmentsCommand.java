package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetDepartmentsCommand implements Command<String> {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> informationData = new ArrayList<>();
        try {
            String query = "SELECT departments.id_department, departments.name, addresses.city, addresses.street" +
                    " FROM departments LEFT JOIN addresses ON " +
                    "departments.id_address = addresses.id_address";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            informationData.add("FetchedDepartments");
            while(resultSet.next()) {
                informationData.add(resultSet.getString(2) + ", " + resultSet.getString(3));
                informationData.add(Integer.toString(resultSet.getInt(1)));
                informationData.add("Street: " + resultSet.getString(4));
            }
        }
        catch (SQLException e) {
            informationData.add("FetchingDepartmentsDataError");
            System.out.println(e.getMessage());
        }
        return informationData;
    }
}
