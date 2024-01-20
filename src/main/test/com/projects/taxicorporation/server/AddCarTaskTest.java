package com.projects.taxicorporation.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

class AddCarTaskTest {

    @Mock
    private Socket socketMock;
    @Mock
    private InputStream inputStreamMock;
    @Mock
    private OutputStream outputStreamMock;

    private AddCarTask addCarTask;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        when(socketMock.getInputStream()).thenReturn(inputStreamMock);
        when(socketMock.getOutputStream()).thenReturn(outputStreamMock);

        addCarTask = new AddCarTask(socketMock);
    }

    @Test
    void runTest() throws IOException, ClassNotFoundException {
        List<String> inputData = new ArrayList<>();
        inputData.add("Car1");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new ObjectOutputStream(baos).writeObject(inputData);
        byte[] inputDataBytes = baos.toByteArray();

        when(inputStreamMock.read(any(byte[].class), anyInt(), anyInt())).thenAnswer(invocation -> {
            byte[] buffer = invocation.getArgument(0);
            int offset = invocation.getArgument(1);
            int length = invocation.getArgument(2);
            System.arraycopy(inputDataBytes, 0, buffer, offset, Math.min(inputDataBytes.length, length));
            return Math.min(inputDataBytes.length, length);
        });
        when(inputStreamMock.read()).thenReturn(inputDataBytes.length, -1);

        addCarTask.run();
        // Weryfikacja czy metody na strumieniach były wywołane
        verify(inputStreamMock).read(any(byte[].class));
    }

    @Test
    public void testSendRequest() {
        AbstractDataBase fakeDatabase = new Database();
        AddCourseTask addCarTask = new AddCourseTask(Mockito.mock(Socket.class));

        List<String> testData = Arrays.asList("driverId", "tripId", "destinationId", "startId");
        addCarTask.data = new ArrayList<>(testData);
    }

    @Test
    void returnFeedbackTest() throws IOException {
        List<String> feedbackData = new ArrayList<>();
        feedbackData.add("Success");

        addCarTask.returnFeedback(feedbackData);

        // Weryfikacja czy dane zostały wysłane
        verify(outputStreamMock).write(anyInt());
        verify(outputStreamMock, times(2)).flush();
    }

    private static class Database extends AbstractDataBase {
        @Override
        public List<String> execute(Command command, List<String> data) {
            // Implement a fake behavior for the fake database
            return Arrays.asList("Course added successfully");
        }

        @Override
        public void connect() throws SQLException, ClassNotFoundException {

        }

        @Override
        public void closeConnect() throws SQLException {

        }
    }
}
