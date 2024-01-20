package com.projects.taxicorporation.server;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

public class AssignDriverDepartmentTaskTest {

    @Test
    public void testSendRequest() {
        // Arrange
        AssignDriverDepartmentTask assignDriverDepartmentTask = new AssignDriverDepartmentTask(Mockito.mock(Socket.class));

        // Set test data for AddCourseTask
        List<String> testData = Arrays.asList("driverId", "tripId", "destinationId", "startId");
        assignDriverDepartmentTask.data = new ArrayList<>(testData);

    }
}
