package com.projects.taxicorporation.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestLogin {
    public static void main(String[] args) {
        List<String> data = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadz login: ");
        data.add(scanner.nextLine());
        System.out.println("Wprowadz haslo: ");
        data.add(scanner.nextLine());
        try(Socket socket = new Socket("localhost", 1523)) {
            OutputStream outputStream = socket.getOutputStream();
            String operation = "LoginTask";
            byte[] messageByteArray = operation.getBytes(StandardCharsets.UTF_8);
            outputStream.write(messageByteArray);
            outputStream.flush();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(data);
            byte[] dataByteArray = bos.toByteArray();
            outputStream.write(dataByteArray);
            outputStream.flush();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
