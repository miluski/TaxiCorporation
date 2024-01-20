package com.projects.taxicorporation.server;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoginCommandTest {
    @Test
    public void testExecuteWithEmptyDataList() throws SQLException {
        LoginCommand loginCommand = new LoginCommand();
        List<String> emptyDataList = new ArrayList<>();
        Connection someMockConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "john", "abcd1234");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            loginCommand.execute(emptyDataList, someMockConnection);
        });
    }
}
