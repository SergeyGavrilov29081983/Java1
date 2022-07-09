package ru.progwards.java2.lessons.http.server;

import ru.progwards.java2.lessons.http.server.app.service.AccountService;
import ru.progwards.java2.lessons.http.server.app.service.StoreService;
import ru.progwards.java2.lessons.http.server.app.service.impl.AccountServiceImpl;
import ru.progwards.java2.lessons.http.server.app.service.impl.StoreServiceImpl;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocketClientHandler implements Runnable {

    private final Socket client;
    private AccountService accountService = new AccountServiceImpl();
    private StoreService storeService = new StoreServiceImpl();

    public SocketClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread started with name:"
                    + Thread.currentThread().getName());
            readResponse();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void readResponse() throws IOException, InterruptedException {

        try {

            BufferedReader request = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
            BufferedWriter response = new BufferedWriter(
                    new OutputStreamWriter(client.getOutputStream()));

            String putDataFromClient = "";
            String requestHeader = "";
            String temp = ".";
            while (!temp.equals("")) {
                temp = request.readLine();
                System.out.println(temp);
                requestHeader += temp + "\n";
            }

            StringBuilder sb = new StringBuilder();
            String[] requestParamValue;
            if (requestHeader.split("\n")[0].contains("GET")) {
                constructResponseHeader(200, sb);
                response.write(sb.toString());
                requestParamValue = handleGetRequest(requestHeader);
                response.write(buildResponse(requestParamValue));
                sb.setLength(0);
                response.flush();

            } else {
                constructResponseHeader(404, sb);
                response.write(sb.toString());
                sb.setLength(0);
                response.flush();
            }
            request.close();
            response.close();

            client.close();
            return;
        } catch (Exception e) {
        }
    }

    private static void constructResponseHeader(int responseCode,
                                                StringBuilder sb) {

        if (responseCode == 200) {

            sb.append("HTTP/1.1 200 OK\r\n");
            sb.append("Date:" + getTimeStamp() + "\r\n");
            sb.append("Server:localhost\r\n");
            sb.append("Content-Type: text/html\r\n");
            sb.append("Connection: Closed\r\n\r\n");

        } else if (responseCode == 404) {

            sb.append("HTTP/1.1 404 Not Found\r\n");
            sb.append("Date:" + getTimeStamp() + "\r\n");
            sb.append("Server:localhost\r\n");
            sb.append("\r\n");
        } else if (responseCode == 304) {
            sb.append("HTTP/1.1 304 Not Modified\r\n");
            sb.append("Date:" + getTimeStamp() + "\r\n");
            sb.append("Server:localhost\r\n");
            sb.append("\r\n");
        }
    }

    private  String buildResponse(String[] requestParam) {
        StringBuilder responseToClient = new StringBuilder();

            double balance = 0.0;
            switch (requestParam[0]) {
                case  ("balance"):
                    balance = accountService.balance(storeService.get(requestParam[1].split("=")[1].split("/")[0]));
                    break;
                case("deposit"):
                    accountService.deposit(storeService.get(requestParam[1].split("=")[1].split("/")[0]),
                            Double.parseDouble(requestParam[1].split("=")[1].split("/")[0]));
                break;
                case("withdraw"):
                    accountService.withdraw(storeService.get(requestParam[1].split("=")[1].split("/")[0]),
                            Double.parseDouble( requestParam[1].split("=")[1].split("/")[1]));
                    break;
                case ("transfer"):
                    accountService.transfer(storeService.get(requestParam[1].split("=")[1].split("/")[0]),
                            storeService.get(requestParam[1].split("=")[1].split("/")[0]), Double.parseDouble( requestParam[1].split("=")[1].split("/")[1]));
                        break;
                default:
                    System.out.println("do nothing");
                    break;
            }
            responseToClient.append("HTTP/1.1 200 OK")
                    .append("\n")
                    .append("Content-Type: text/html; charset=utf-8")
                    .append("\n")
                    .append(balance);

        return responseToClient.toString();
    }

    private static String getTimeStamp() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    private static String[] handleGetRequest(String request) {

        return request.split("\\?")[1].split("&");
    }
}