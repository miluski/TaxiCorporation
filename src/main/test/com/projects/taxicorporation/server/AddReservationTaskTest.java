package com.projects.taxicorporation.server;

import com.projects.taxicorporation.client.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AddReservationTaskTest {

    private Socket mockSocket;
    private InputStream mockInputStream;
    private OutputStream mockOutputStream;
    private ByteArrayOutputStream bos;
    private AddReservationTask addReservationTask;

    @BeforeEach
    public void setUp() throws IOException {
        mockSocket = Mockito.mock(Socket.class);
        mockInputStream = Mockito.mock(InputStream.class);
        mockOutputStream = Mockito.mock(OutputStream.class);
        bos = new ByteArrayOutputStream();

        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        when(mockSocket.getOutputStream()).thenReturn(mockOutputStream);

        addReservationTask = new AddReservationTask(mockSocket);
    }

    @Test
    public void testRun() throws IOException, ClassNotFoundException {
        // Arrange
        List<String> testData = Arrays.asList("ReservationId", "UserId", "TaxiId", "PickupLocation");
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(testData);
        byte[] testDataBytes = bos.toByteArray();

        when(mockInputStream.read()).thenReturn((int) testDataBytes.length).thenReturn(-1);
        when(mockInputStream.read(any(byte[].class), anyInt(), anyInt())).thenAnswer(invocation -> {
            byte[] buffer = invocation.getArgument(0);
            System.arraycopy(testDataBytes, 0, buffer, 0, testDataBytes.length);
            return testDataBytes.length;
        });

        // Act
        addReservationTask.run();
    }

    @Test
    public void testSendRequest() {
        AddReservationTask addReservationTask = new AddReservationTask(Mockito.mock(Socket.class));

        List<String> testData = Arrays.asList("reservationId", "departmentName", "location");

        // Set test data for AddReservationTask
        Database database = new Database();

        assertTrue((boolean)database.sendRequest("AddReservation", testData));
    }

}
