package com.projects.taxicorporation.server;

import com.projects.taxicorporation.client.*;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTaskTest {

    @Test
    public void testRun_ChiefExecutive() throws IOException {
        List<String> testData = Arrays.asList("1", "jan.kowal@gmail.com", "jan", "kowal#445", "swietokrzyski", "kielce", "test_street", "0", "1");

        // Set test data for RenameDepartmentTask
        Database database = new Database();

        List<String>  dbResponse = (List<String>) database.sendRequest("RegisterTask", testData);

        assertEquals("1", dbResponse.get(0));
        assertEquals("jan.kowal@gmail.com", dbResponse.get(1));
        assertEquals("jan", dbResponse.get(2));
        assertEquals("kowal#445", dbResponse.get(3));
        assertEquals("swietokrzyski", dbResponse.get(4));
        assertEquals("kielce", dbResponse.get(5));
        assertEquals("test_street", dbResponse.get(6));
        assertEquals("0", dbResponse.get(7));
        assertEquals("1", dbResponse.get(8));
    }

    @Test
    public void testRun_Manager() throws IOException {
        List<String> testData = Arrays.asList("1", "jan.kowal@gmail.com", "jan", "kowal#445", "swietokrzyski", "kielce", "test_street", "0", "2");

        // Set test data for RenameDepartmentTask
        Database database = new Database();

        List<String>  dbResponse = (List<String>) database.sendRequest("RegisterTask", testData);

        assertEquals("1", dbResponse.get(0));
        assertEquals("jan.kowal@gmail.com", dbResponse.get(1));
        assertEquals("jan", dbResponse.get(2));
        assertEquals("kowal#445", dbResponse.get(3));
        assertEquals("swietokrzyski", dbResponse.get(4));
        assertEquals("kielce", dbResponse.get(5));
        assertEquals("test_street", dbResponse.get(6));
        assertEquals("0", dbResponse.get(7));
        assertEquals("2", dbResponse.get(8));
    }

    @Test
    public void testRun_TechnicalWorker() throws IOException {
        List<String> testData = Arrays.asList("1", "jan.kowal@gmail.com", "jan", "kowal#445", "swietokrzyski", "kielce", "test_street", "0", "3");

        // Set test data for RenameDepartmentTask
        Database database = new Database();

        List<String>  dbResponse = (List<String>) database.sendRequest("RegisterTask", testData);

        assertEquals("1", dbResponse.get(0));
        assertEquals("jan.kowal@gmail.com", dbResponse.get(1));
        assertEquals("jan", dbResponse.get(2));
        assertEquals("kowal#445", dbResponse.get(3));
        assertEquals("swietokrzyski", dbResponse.get(4));
        assertEquals("kielce", dbResponse.get(5));
        assertEquals("test_street", dbResponse.get(6));
        assertEquals("0", dbResponse.get(7));
        assertEquals("3", dbResponse.get(8));
    }

    @Test
    public void testRun_Driver() throws IOException {
        List<String> testData = Arrays.asList("1", "jan.kowal@gmail.com", "jan", "kowal#445", "swietokrzyski", "kielce", "test_street", "0", "4");

        // Set test data for RenameDepartmentTask
        Database database = new Database();

        List<String>  dbResponse = (List<String>) database.sendRequest("RegisterTask", testData);

        assertEquals("1", dbResponse.get(0));
        assertEquals("jan.kowal@gmail.com", dbResponse.get(1));
        assertEquals("jan", dbResponse.get(2));
        assertEquals("kowal#445", dbResponse.get(3));
        assertEquals("swietokrzyski", dbResponse.get(4));
        assertEquals("kielce", dbResponse.get(5));
        assertEquals("test_street", dbResponse.get(6));
        assertEquals("0", dbResponse.get(7));
        assertEquals("4", dbResponse.get(8));
    }

    @Test
    public void testRun_Client() throws IOException {
        List<String> testData = Arrays.asList("1", "jan.kowal@gmail.com", "jan", "kowal#445", "swietokrzyski", "kielce", "test_street", "0", "5");

        // Set test data for RenameDepartmentTask
        Database database = new Database();

        List<String>  dbResponse = (List<String>) database.sendRequest("RegisterTask", testData);

        assertEquals("1", dbResponse.get(0));
        assertEquals("jan.kowal@gmail.com", dbResponse.get(1));
        assertEquals("jan", dbResponse.get(2));
        assertEquals("kowal#445", dbResponse.get(3));
        assertEquals("swietokrzyski", dbResponse.get(4));
        assertEquals("kielce", dbResponse.get(5));
        assertEquals("test_street", dbResponse.get(6));
        assertEquals("0", dbResponse.get(7));
        assertEquals("5", dbResponse.get(8));
    }

    @Test
    public void testRun_Mechanic() throws IOException {
        List<String> testData = Arrays.asList("1", "jan.kowal@gmail.com", "jan", "kowal#445", "swietokrzyski", "kielce", "test_street", "0", "6");

        // Set test data for RenameDepartmentTask
        Database database = new Database();

        List<String>  dbResponse = (List<String>) database.sendRequest("RegisterTask", testData);

        assertEquals("1", dbResponse.get(0));
        assertEquals("jan.kowal@gmail.com", dbResponse.get(1));
        assertEquals("jan", dbResponse.get(2));
        assertEquals("kowal#445", dbResponse.get(3));
        assertEquals("swietokrzyski", dbResponse.get(4));
        assertEquals("kielce", dbResponse.get(5));
        assertEquals("test_street", dbResponse.get(6));
        assertEquals("0", dbResponse.get(7));
        assertEquals("6", dbResponse.get(8));
    }

}
