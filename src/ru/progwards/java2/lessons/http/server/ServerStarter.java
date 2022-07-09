package ru.progwards.java2.lessons.http.server;

import java.io.IOException;

public class ServerStarter {
    public static void main(String[] args) throws InterruptedException {


	    int portNumber = 5554;
        try {
            // initializing the Socket Server
            MySocketServer socketServer = new MySocketServer(
                    portNumber);
            socketServer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
