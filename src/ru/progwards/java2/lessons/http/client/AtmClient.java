package ru.progwards.java2.lessons.http.client;

import ru.progwards.java2.lessons.http.server.app.model.Account;
import ru.progwards.java2.lessons.http.server.app.service.AccountService;

import java.io.*;
import java.net.Socket;

public class AtmClient {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = null;
        clientSocket = new Socket("127.0.0.1", 5554);

        System.out.println("======================================");
        System.out.println("Connected");
        System.out.println("======================================");

        // Declare a writer to this url
        PrintWriter request = new PrintWriter(clientSocket.getOutputStream(),
                true);

        // Declare a listener to this url
        BufferedReader response = new BufferedReader(new InputStreamReader(
                clientSocket.getInputStream()));

        // Sending request to the server
        // Building HTTP request header
        request.print("GET /" + "/resource?balance&id=01" + "/ HTTP/1.1\r\n"); // "+path+"
        request.print("Host: " + "127.0.0.1" + "\r\n");
        request.print("/resource?balance&id=01");
        request.print("Connection: close\r\n");
        request.print("Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\r\n");
        request.print("\r\n");
        request.flush();
        System.out.println("Request Sent!");
        System.out.println("======================================");

        // Receiving response from server
        String responseLine;
        while ((responseLine = response.readLine()) != null) {
            System.out.println(responseLine);
        }
        System.out.println("======================================");
        System.out.println("Response Recieved!!");
        System.out.println("======================================");

        response.close();
        request.close();
        clientSocket.close();

    }
}
