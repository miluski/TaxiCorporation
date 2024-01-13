package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RenameDepartmentCommand implements Command<String> {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> informationData = new ArrayList<>();
        try {
            getAddressId(connect, Integer.parseInt(data.get(0)), data.get(1), data.get(3));
            String query = "UPDATE departments SET name = ? WHERE id_department = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, data.get(2));
            preparedStatement.setInt(2, Integer.parseInt(data.get(0)));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                informationData.add("EditDepartmentSuccessfull");
            else
                informationData.add("EditDepartmentUnsuccessfull");
        } catch (SQLException e) {
            informationData.add("RenameDepartmentUnsuccessfull");
            System.out.println(e.getMessage());
        }
        return informationData;
    }
    private void getAddressId(Connection connect, int departmentId, String city, String street) {
        try {
            String query = "SELECT id_address FROM departments WHERE id_department = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, departmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                updateAddress(connect, resultSet.getInt(1), city, street);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void updateAddress(Connection connect, int addressId, String city, String street) {
        try {
            String query = "UPDATE addresses SET city = ?, street = ? WHERE id_address = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, city);
            preparedStatement.setString(2, street);
            preparedStatement.setInt(3, addressId);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
