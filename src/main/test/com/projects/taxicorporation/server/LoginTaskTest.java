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

public class LoginTaskTest {

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
        List<String> testData = Arrays.asList("jan_kowal", "pass#123");

        Database database = new Database();

        List<String>  dbResponse = (List<String>) database.sendRequest("Login", testData);

        assertEquals("jan_kowal", dbResponse.get(0));
        assertEquals("jan_kowal@gmail.com", dbResponse.get(1));
        assertEquals("pass#123", dbResponse.get(2));
    }

    private byte[] serializeTestData() throws IOException {
        List<String> testData = Arrays.asList("DepartmentName", "NewDepartmentName");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(testData);
        return bos.toByteArray();
    }
}
