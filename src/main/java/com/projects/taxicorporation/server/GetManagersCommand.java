package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetManagersCommand implements Command {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> informationData = new ArrayList<>();
        try {
            String query = "SELECT users.id_user, users.email FROM users WHERE id_user_role = 2";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            informationData.add("FetchedManagers");
            while(resultSet.next()) {
                informationData.add(resultSet.getString(2));
                informationData.add(Integer.toString(resultSet.getInt(1)));
            }
        }
        catch (SQLException e) {
            informationData.add("FetchedManagersDataError");
            System.out.println(e.getMessage());
        }
        return informationData;
    }
}
