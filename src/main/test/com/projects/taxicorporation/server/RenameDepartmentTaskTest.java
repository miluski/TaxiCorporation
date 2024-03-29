package com.projects.taxicorporation.server;

import com.projects.taxicorporation.client.Database;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class RenameDepartmentTaskTest {

    @Test
    public void testRun() throws IOException {
        Socket mockSocket = Mockito.mock(Socket.class);
        InputStream mockInputStream = new ByteArrayInputStream(serializeTestData());
        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        RenameDepartmentTask renameDepartmentTask = new RenameDepartmentTask(mockSocket);

        renameDepartmentTask.run();
    }

    @Test
    public void testSendRequest_RenameDepartment() {
        // Arrange
        List<String> testData = Arrays.asList("departmentId", "newDepartmentName");

        Database database = new Database();

        // Act
        Object dbResponse = database.sendRequest("RenameDepartment", testData);

        // Assert
        assertFalse(Boolean.parseBoolean((String) dbResponse));
    }

    private byte[] serializeTestData() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        return bos.toByteArray();
    }
}
