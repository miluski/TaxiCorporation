package com.projects.taxicorporation.client;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Database extends AbstractDataBase {
    List<String> data;

    @Override
    public Object sendRequest(String command, List<String> data) {
        if (command.startsWith("Add")) {
            return true;
        }
        if (command.startsWith("Rename")) {
            return data.get(1);
        }
        if (command.startsWith("Logout")) {
            return true;
        }
        if (command.startsWith("Login")) {
            return Arrays.asList("jan_kowal", "jan_kowal@gmail.com", "pass#123");
        }
        if (command.startsWith("GetManagers")) {
            return Arrays.asList("1", "jan_kowal@gmail.com", "2", "szczecinski@gmail.com", "3", "jaruzelski@gmail.com");
        }
        if (command.startsWith("GetDepartments")) {
            return Arrays.asList("1", "Politechnika Swietokrzyska", "2", "Korona", "3", "Echo");
        }
        return data;
    }

    @Override
    public void connect() throws SQLException, ClassNotFoundException {

    }

    @Override
    public void closeConnect() throws SQLException {

    }
}