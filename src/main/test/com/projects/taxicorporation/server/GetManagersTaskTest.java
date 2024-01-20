package com.projects.taxicorporation.server;

import com.projects.taxicorporation.client.Database;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetManagersTaskTest {

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

        List<String> dbResponse = (List<String>) database.sendRequest("GetManagers", testData);

        assertEquals("1", dbResponse.get(0));
        assertEquals("jan_kowal@gmail.com", dbResponse.get(1));

        assertEquals("2", dbResponse.get(2));
        assertEquals("szczecinski@gmail.com", dbResponse.get(3));

        assertEquals("3", dbResponse.get(4));
        assertEquals("jaruzelski@gmail.com", dbResponse.get(5));
    }

    private byte[] serializeTestData() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        return bos.toByteArray();
    }
}
