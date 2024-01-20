package com.projects.taxicorporation.server;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetDriversCommandTest {

    @Test
    void testExecute() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        GetDriversCommand command = new GetDriversCommand();
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);  // Two rows of data
        when(mockResultSet.getString(2)).thenReturn("DriverEmail1", "DriverEmail2");
        when(mockResultSet.getInt(1)).thenReturn(1, 2);
        List<String> result = command.execute(new ArrayList<>(), mockConnection);
        assertEquals(5, result.size());
        assertEquals("FetchedDrivers", result.get(0));
        assertEquals("DriverEmail1", result.get(1));
        assertEquals("1", result.get(2));
        assertEquals("DriverEmail2", result.get(3));
        assertEquals("2", result.get(4));
        verify(mockStatement).executeQuery("SELECT users.id_user, users.email FROM users WHERE id_user_role = 5");
    }
}
