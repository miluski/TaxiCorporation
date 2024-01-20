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
        if (command.startsWith("Delete")) {
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
        if (command.startsWith("GetCars")) {
            return Arrays.asList("1", "Opel", "2", "Ford", "3", "Toyota");
        }
        if (command.startsWith("GetAvailableReservations")) {
            return Arrays.asList("1","Swietokrzyska", "Pietroszewska", "21.01.2024", "2","Warszawska", "Kochanowska", "15.01.2024", "3","Rynkowa", "Kowalska", "25.01.2024");
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