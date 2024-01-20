package com.projects.taxicorporation.server;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AddReservationCommandTest {

    @Test
    void testExecuteWithEmptyDataList() throws SQLException {
        AddReservationCommand addReservationCommand = new AddReservationCommand();
        List<String> emptyDataList = new ArrayList<>();
        Connection someMockConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            addReservationCommand.execute(emptyDataList, someMockConnection);
        });
    }

}