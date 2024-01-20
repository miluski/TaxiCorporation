package com.projects.taxicorporation.server;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogoutCommandTest {
    @Test
    public void testExecuteWithEmptyDataList() throws SQLException {
        LogoutCommand logoutCommand = new LogoutCommand();
        List<String> emptyDataList = new ArrayList<>();
        Connection someMockConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "john", "abcd1234");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            logoutCommand.execute(emptyDataList, someMockConnection);
        });
    }
}
