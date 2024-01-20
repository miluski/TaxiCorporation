package com.projects.taxicorporation.server;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegisterCommandTest {
    @Test
    public void testExecuteWithEmptyDataList() throws SQLException {
        RegisterCommand registerCommand = new RegisterCommand();
        List<String> emptyDataList = new ArrayList<>();
        Connection someMockConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            registerCommand.execute(emptyDataList, someMockConnection);
        });
    }
}
