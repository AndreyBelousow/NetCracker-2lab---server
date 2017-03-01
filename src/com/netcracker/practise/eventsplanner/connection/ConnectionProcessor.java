package com.netcracker.practise.eventsplanner.connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Provides basic multithread server logic:
 * client connection establishment, threading;
 * sends client's messages to {@link Server}
 * @author A.Belousow
 * */
public class ConnectionProcessor {

    private Server server;

    private static int threadPoolSize = 10;

    private static int port = 7777;

    private static class SocketProcessor implements Runnable {

        private Socket clientSocket;
        private InputStream input;
        private OutputStream output;
        private int connectionID;

        private SocketProcessor(Socket s, int connectionID) throws IOException {
            this.clientSocket = s;
            this.input = s.getInputStream();
            this.output = s.getOutputStream();
            this.connectionID = connectionID;
        }

        public void run() {

        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket socket = new ServerSocket(port);

        ExecutorService service = Executors.newFixedThreadPool(threadPoolSize);

        System.err.println("I am server, and i am waiting...");

        while (true) {
            Socket s = socket.accept();
            for(int i = 0; i < 10; i++) {
                service.submit(new SocketProcessor(s,i));
                System.err.println("Someone connected...");
            }
        }
    }
}
