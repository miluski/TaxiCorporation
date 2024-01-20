package com.projects.taxicorporation.server;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetCarsCommandTest {

    @Test
    void testExecute() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        GetCarsCommand command = new GetCarsCommand();
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString(2)).thenReturn("CarModel1", "CarModel2");
        when(mockResultSet.getString(3)).thenReturn("2022", "2023");
        when(mockResultSet.getInt(1)).thenReturn(1, 2);
        List<String> result = command.execute(new ArrayList<>(), mockConnection);
        assertEquals(5, result.size());
        assertEquals("FetchedCars", result.get(0));
        assertEquals("CarModel1, 2022", result.get(1));
        assertEquals("1", result.get(2));
        assertEquals("CarModel2, 2023", result.get(3));
        assertEquals("2", result.get(4));
        verify(mockStatement).executeQuery("SELECT id_car, model, model_year FROM cars");
    }
}
