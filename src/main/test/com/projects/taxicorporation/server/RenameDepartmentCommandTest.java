package com.projects.taxicorporation.server;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RenameDepartmentCommandTest {
    @Test
    public void testExecuteWithEmptyDataList() throws SQLException {
        RenameDepartmentCommand renameDepartmentCommand = new RenameDepartmentCommand();
        List<String> emptyDataList = new ArrayList<>();
        Connection someMockConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            renameDepartmentCommand.execute(emptyDataList, someMockConnection);
        });
    }
}
