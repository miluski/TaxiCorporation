package com.projects.taxicorporation.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Wzorzec projektowy polecenie (command)
 */
public class RegisterCommand implements Command<String> {
    private Connection connect;
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        this.connect = connect;
        List<String> informationData = new ArrayList<>();
        boolean isAccountExists = checkIfAccountExists(data);
        if (isAccountExists)
            informationData.add("AccountAlreadyExists");
        else {
            try {
                int userRoleNumber = getUserRoleNumber(data);
                String query = "INSERT INTO users (id_user, username, password, email, department, city, street, id_user_role) " +
                        "VALUES(users_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, data.get(0));
                preparedStatement.setString(2, data.get(1));
                preparedStatement.setString(3, data.get(2));
                preparedStatement.setString(4, data.get(3));
                preparedStatement.setString(5, data.get(4));
                preparedStatement.setString(6, data.get(5));
                preparedStatement.setInt(7, userRoleNumber);
                preparedStatement.execute();
                if(userRoleNumber==5)
                    addNewTaxiDriver();
                informationData.add("SuccessfullRegister");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                informationData.add("UnexpectedError");
            }
        }
        return informationData;
    }

    private int getUserRoleNumber(List<String> data) {
        switch (data.get(3)) {
            case "ChiefExecutive" -> {
                return 1;
            }
            case "Manager" -> {
                return 2;
            }
            case "TechnicalWorker" -> {
                return 3;
            }
            case "Mechanic" -> {
                return 4;
            }
            case "Driver" -> {
                return 5;
            }
        }
        return 6;
    }

    private boolean checkIfAccountExists(List<String> data) {
        try {
            String query = "SELECT 1 FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, data.get(0));
            ResultSet result = preparedStatement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    private void addNewTaxiDriver() {
        try {
            int driverUserId = getLastAddedDriverId();
            String query = "INSERT INTO taxi_drivers(id_driver, id_user) VALUES (taxi_drivers_seq.NEXTVAL, ?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1 ,driverUserId);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private int getLastAddedDriverId() {
        try {
            String query = "SELECT MAX(id_users) FROM users";
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
