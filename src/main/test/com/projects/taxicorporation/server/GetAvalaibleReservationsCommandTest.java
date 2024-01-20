package com.projects.taxicorporation.server;

import com.projects.taxicorporation.models.RouteInfo;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetAvailableReservationsCommandTest {

    @Test
    void testExecute() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        GetAvailableReservationsCommand command = new GetAvailableReservationsCommand();
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);  // One row of data
        when(mockResultSet.getString("departure_name")).thenReturn("DepartureStreet");
        when(mockResultSet.getString("arrival_name")).thenReturn("ArrivalStreet");
        when(mockResultSet.getString("departure_date")).thenReturn("2024-01-20");
        when(mockResultSet.getInt("id_reservation")).thenReturn(1);
        List<RouteInfo> result = command.execute(new ArrayList<>(), mockConnection);
        assertEquals(1, result.size());
        assertEquals("DepartureStreet", result.get(0).departureName);
        assertEquals("ArrivalStreet", result.get(0).arrivalName);
        assertEquals("2024-01-20", result.get(0).departureDate);
        assertEquals(1, result.get(0).reservationId);
        verify(mockStatement).executeQuery("SELECT reservations.id_reservation, departures.hour AS departure_date, " +
                "(SELECT addresses.street FROM addresses WHERE addresses.id_address = departures.id_address) AS departure_name, " +
                "(SELECT addresses.street FROM addresses WHERE addresses.id_address = arrivals.id_address) AS arrival_name " +
                "FROM reservations " +
                "JOIN departures ON reservations.id_departure = departures.id_departure " +
                "JOIN arrivals ON reservations.id_arrival = arrivals.id_arrival ");
    }
}
