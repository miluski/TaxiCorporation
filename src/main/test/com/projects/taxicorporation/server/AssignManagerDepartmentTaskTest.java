package com.projects.taxicorporation.server;

import com.projects.taxicorporation.client.Database;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class AssignManagerDepartmentTaskTest {

    @Test
    public void testRun() throws IOException, ClassNotFoundException {
        // Arrange
        Socket mockSocket = Mockito.mock(Socket.class);
        InputStream mockInputStream = new ByteArrayInputStream(serializeTestData());
        when(mockSocket.getInputStream()).thenReturn(mockInputStream);

        AssignManagerDepartmentTask assignManagerDepartmentTask = new AssignManagerDepartmentTask(mockSocket);

        // Act
        assignManagerDepartmentTask.run();
    }

    @Test
    public void testSendRequest() {
        AssignManagerDepartmentTask assignManagerDepartmentTask = new AssignManagerDepartmentTask(Mockito.mock(Socket.class));

        List<String> testData = Arrays.asList("departmentId", "departmentName", "location");

        // Set test data for AddDepartmentTask
        Database database = new Database();

        assertTrue((boolean)database.sendRequest("AssignManagerDepartment", testData));
    }

    private byte[] serializeTestData() throws IOException {
        List<String> testData = Arrays.asList("managerId", "departmentId");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(testData);
        return bos.toByteArray();
    }
}
