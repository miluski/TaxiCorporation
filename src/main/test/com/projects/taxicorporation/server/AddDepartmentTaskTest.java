package com.projects.taxicorporation.server;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddDepartmentTaskTest {
    @Test
    public void testRun() throws IOException, ClassNotFoundException {
        Socket mockSocket = Mockito.mock(Socket.class);
        InputStream mockInputStream = new ByteArrayInputStream(serializeTestData());
        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        AddDepartmentTask addDepartmentTask = new AddDepartmentTask(mockSocket);

        addDepartmentTask.run();
    }

    @Test
    public void testSendRequest() {
        AbstractDataBase fakeDatabase = new Database();
        AddDepartmentTask addDepartmentTask = new AddDepartmentTask(Mockito.mock(Socket.class));

        List<String> testData = Arrays.asList("departmentName", "departmentLocation");
        addDepartmentTask.data = new ArrayList<>(testData);
    }

    private byte[] serializeTestData() throws IOException {
        List<String> testData = Arrays.asList("DepartmentName", "DepartmentLocation");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(testData);
        return bos.toByteArray();
    }

    @Test
    void returnFeedbackTest() throws IOException {
        // Mockowanie Socket i OutputStream
        Socket socketMock = mock(Socket.class);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(socketMock.getOutputStream()).thenReturn(outputStream);

        // Tworzenie instancji AddDepartmentTask
        AddDepartmentTask addDepartmentTask = new AddDepartmentTask(socketMock);

        // Dane do zwrotu
        List<String> feedbackData = new ArrayList<>();
        feedbackData.add("Success");

        // Wywołanie metody returnFeedback
        addDepartmentTask.returnFeedback(feedbackData);

        // Weryfikacja czy dane zostały wysłane
        verify(socketMock).getOutputStream(); // Sprawdza, czy getOutputStream() został wywołany
        assertNotEquals(0, outputStream.size()); // Sprawdza, czy dane zostały faktycznie zapisane do strumienia
    }

    private static class Database extends AbstractDataBase {
        @Override
        public List<String> execute(Command command, List<String> data) {
            // Implement a fake behavior for the fake database
            return Arrays.asList("Department added successfully");
        }

        @Override
        public void connect() throws SQLException, ClassNotFoundException {

        }

        @Override
        public void closeConnect() throws SQLException {

        }
    }
}
