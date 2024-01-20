package com.projects.taxicorporation.server;

import com.projects.taxicorporation.client.Database;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class DeleteReservationTaskTest {

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
        List<String> testData = Arrays.asList("35");

        Database database = new Database();

        Object dbResponse = database.sendRequest("DeleteReservation", testData);

        assertTrue((boolean) dbResponse);
    }

    private byte[] serializeTestData() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        return bos.toByteArray();
    }
}
