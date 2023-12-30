package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Wzorzec projektowy polecenie (command)
 */
public class RegisterCommand implements Command {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        List<String> informationData = new ArrayList<>();
        boolean isAccountExists = checkIfAccountExists(data, connect);
        if (isAccountExists)
            informationData.add("AccountAlreadyExists");
        else {
            try {
                int userRoleNumber = getUserRoleNumber(data);
                String query = "INSERT INTO users (id_user, username, password, email, rank, department, city, street, id_user_role) " +
                        "VALUES(users_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, data.get(0));
                preparedStatement.setString(2, data.get(1));
                preparedStatement.setString(3, data.get(2));
                preparedStatement.setString(4, data.get(3));
                preparedStatement.setString(5, data.get(4));
                preparedStatement.setString(6, data.get(5));
                preparedStatement.setString(7, data.get(6));
                preparedStatement.setInt(8, userRoleNumber);
                preparedStatement.execute();
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

    private boolean checkIfAccountExists(List<String> data, Connection connect) {
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
}
