package com.projects.taxicorporation.server;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;

public class AddCourseTaskTest {

    @Test
    public void testRun() throws IOException {
        // Arrange
        Socket mockSocket = Mockito.mock(Socket.class);
        InputStream mockInputStream = new ByteArrayInputStream(serializeTestData());
        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        AddCourseTask addCourseTask = new AddCourseTask(mockSocket);

        // Act
        addCourseTask.run();
    }

    @Test
    public void testSendRequest() {
        // Arrange
        /*AbstractDataBase fakeDatabase = new Database();*/
        AddCourseTask addCourseTask = new AddCourseTask(Mockito.mock(Socket.class));

        // Set test data for AddCourseTask
        List<String> testData = Arrays.asList("driverId", "tripId", "destinationId", "startId");
        addCourseTask.data = new ArrayList<>(testData);
    }

    private byte[] serializeTestData() throws IOException {
        List<String> testData = Arrays.asList("CourseName", "InstructorName");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(testData);
        return bos.toByteArray();
    }
}
