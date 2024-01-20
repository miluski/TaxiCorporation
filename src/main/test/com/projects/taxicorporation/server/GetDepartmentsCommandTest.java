package com.projects.taxicorporation.server;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetDepartmentsCommandTest {

    @Test
    void testExecute() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        GetDepartmentsCommand command = new GetDepartmentsCommand();
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);  // Two rows of data
        when(mockResultSet.getString(2)).thenReturn("DepartmentName1", "DepartmentName2");
        when(mockResultSet.getString(3)).thenReturn("City1", "City2");
        when(mockResultSet.getString(4)).thenReturn("Street1", "Street2");
        when(mockResultSet.getInt(1)).thenReturn(1, 2);
        List<String> result = command.execute(new ArrayList<>(), mockConnection);
        assertEquals(7, result.size());
        assertEquals("FetchedDepartments", result.get(0));
        assertEquals("DepartmentName1, City1", result.get(1));
        assertEquals("1", result.get(2));
        assertEquals("Street: Street1", result.get(3));
        assertEquals("DepartmentName2, City2", result.get(4));
        assertEquals("2", result.get(5));
        assertEquals("Street: Street2", result.get(6));
        verify(mockStatement).executeQuery("SELECT departments.id_department, departments.name, addresses.city, addresses.street" +
                " FROM departments LEFT JOIN addresses ON " +
                "departments.id_address = addresses.id_address");
    }
}
