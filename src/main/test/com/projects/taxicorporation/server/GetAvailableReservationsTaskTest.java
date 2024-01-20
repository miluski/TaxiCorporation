package com.projects.taxicorporation.server;

import com.projects.taxicorporation.client.Database;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.*;
import java.net.Socket;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetAvailableReservationsTaskTest {

    @Test
    public void testRun() throws IOException {
        Socket mockSocket = Mockito.mock(Socket.class);
        InputStream mockInputStream = new ByteArrayInputStream(serializeTestData());
        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        LogoutTask logoutTask = new LogoutTask(mockSocket);

        logoutTask.run();
    }
    @Test
    public void testSendRequest() {
        List<String> testData = List.of("");

        Database database = new Database();

        List<String> dbResponse = (List<String>) database.sendRequest("GetAvailableReservations", testData);

        assertEquals("1", dbResponse.get(0));
        assertEquals("Swietokrzyska", dbResponse.get(1));
        assertEquals("Pietroszewska", dbResponse.get(2));
        assertEquals("21.01.2024", dbResponse.get(3));

        assertEquals("2", dbResponse.get(4));
        assertEquals("Warszawska", dbResponse.get(5));
        assertEquals("Kochanowska", dbResponse.get(6));
        assertEquals("15.01.2024", dbResponse.get(7));

        assertEquals("3", dbResponse.get(8));
        assertEquals("Rynkowa", dbResponse.get(9));
        assertEquals("Kowalska", dbResponse.get(10));
        assertEquals("25.01.2024", dbResponse.get(11));
    }

    private byte[] serializeTestData() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        return bos.toByteArray();
    }
}
