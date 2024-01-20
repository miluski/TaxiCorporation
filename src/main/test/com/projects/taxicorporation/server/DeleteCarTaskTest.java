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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeleteCarTaskTest {

    @Test
    public void testRun() throws IOException, ClassNotFoundException {
        // Arrange
        Socket mockSocket = Mockito.mock(Socket.class);
        InputStream mockInputStream = new ByteArrayInputStream(serializeTestData());
        when(mockSocket.getInputStream()).thenReturn(mockInputStream);

        ByteArrayOutputStream mockOutputStream = new ByteArrayOutputStream();
        when(mockSocket.getOutputStream()).thenReturn(new ByteArrayOutputStream());

        DeleteCarTask deleteCarTask = new DeleteCarTask(mockSocket);

        // Act
        deleteCarTask.run();
    }

    @Test
    public void testSendRequest() {
        // Arrange
        DeleteCarTask deleteCarTask = new DeleteCarTask(Mockito.mock(Socket.class));

        List<String> testData = new ArrayList<>();
        testData.add("CarId");
        Database database = new Database();
        assertTrue((boolean)database.sendRequest("DeleteCar", testData));
    }

    private byte[] serializeTestData() throws IOException {
        List<String> testData = new ArrayList<>();
        testData.add("CarId");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(testData);
        return bos.toByteArray();
    }
}
