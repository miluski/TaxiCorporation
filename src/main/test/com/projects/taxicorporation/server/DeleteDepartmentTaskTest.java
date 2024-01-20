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

public class DeleteDepartmentTaskTest {

    @Test
    public void testRun() throws IOException {
        Socket mockSocket = Mockito.mock(Socket.class);
        InputStream mockInputStream = new ByteArrayInputStream(serializeTestData());
        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        DeleteDepartmentTask deleteDepartmentTask = new DeleteDepartmentTask(mockSocket);

        deleteDepartmentTask.run();
    }

    @Test
    public void testSendRequest() {
        List<String> testData = Arrays.asList("Politechnika Swietokrzyska", "Korona", "Echo");

        Database database = new Database();

        List<String>  dbResponse = (List<String>) database.sendRequest("GetDepartments", testData);

        assertEquals("1", dbResponse.get(0));
        assertEquals("Politechnika Swietokrzyska", dbResponse.get(1));
        assertEquals("2", dbResponse.get(2));
        assertEquals("Korona", dbResponse.get(3));
        assertEquals("3", dbResponse.get(4));
        assertEquals("Echo", dbResponse.get(5));
    }

    private byte[] serializeTestData() throws IOException {
        List<String> testData = Arrays.asList("DepartmentName", "NewDepartmentName");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(testData);
        return bos.toByteArray();
    }
}