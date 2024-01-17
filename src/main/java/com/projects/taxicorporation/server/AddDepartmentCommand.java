package com.projects.taxicorporation.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddDepartmentCommand implements Command<String> {
    private final List<String> data = new ArrayList<>();
    private Connection connect;
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> informationData = new ArrayList<>();
        this.data.addAll(data);
        this.connect = connect;
        if(isInsertedDataIntoAddressesTable()) {
            if(isInsertedDataIntoDepartmentsTable())
                informationData.add("AddDepartmentSuccessfull");
        } else {
            informationData.add("AddDepartmentUnsuccessfull");
        }
        return informationData;
    }
    private boolean isInsertedDataIntoDepartmentsTable() {
        try {
            String query = "INSERT INTO departments (id_department, name, id_address) VALUES (departments_seq.nextval, ?, ?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, data.get(2));
            preparedStatement.setInt(2, getLastAdressId());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    private boolean isInsertedDataIntoAddressesTable() {
        try {
            String query = "INSERT INTO addresses (id_address, city, street) VALUES (addresses_seq.nextval, ?, ?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, data.get(0));
            preparedStatement.setString(2, data.get(1));
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    private int getLastAdressId() {
        try {
            String query = "SELECT MAX(id_address) FROM addresses";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
}
