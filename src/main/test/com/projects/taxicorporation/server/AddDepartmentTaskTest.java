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

public class AddDepartmentTaskTest {

    @Test
    public void testRun() throws IOException {
        // Arrange
        Socket mockSocket = Mockito.mock(Socket.class);
        InputStream mockInputStream = new ByteArrayInputStream(serializeTestData());
        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        AddDepartmentTask addDepartmentTask = new AddDepartmentTask(mockSocket);

        // Act
        addDepartmentTask.run();
    }

    @Test
    public void testSendRequest() {
        AddDepartmentTask addDepartmentTask = new AddDepartmentTask(Mockito.mock(Socket.class));

        List<String> testData = Arrays.asList("departmentId", "departmentName", "location");

        // Set test data for AddDepartmentTask
        Database database = new Database();

        assertTrue((boolean)database.sendRequest("AddDepartment", testData));
    }

    private byte[] serializeTestData() throws IOException {
        List<String> testData = Arrays.asList("DepartmentName", "Location");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(testData);
        return bos.toByteArray();
    }
}
