package com.netcracker.practise.eventsplanner.controller.connection;

import com.netcracker.practise.eventsplanner.controller.ServerLogicProcessor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Provides basic multithread serverLogicProcessor logic:
 * client connection establishment, threading;
 * sends client's messages to {@link ServerLogicProcessor}
 * @author A.Belousow
 * */
public class ConnectionProcessor {

    private static ServerLogicProcessor serverLogicProcessor;

    private static int threadPoolSize = 10;

    private static int port = 7777;

    private static class SocketProcessor implements Runnable {

        private Socket clientSocket;
        private InputStream input;
        private OutputStream output;

        private SocketProcessor(Socket s) throws IOException {
            this.clientSocket = s;
            this.input = s.getInputStream();
            this.output = s.getOutputStream();
        }

        public void run() {
            try {
                serverLogicProcessor.onConnect(input, output);
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Port "+clientSocket.getLocalPort()+" disconnected");
        }
    }

    public static void main(String[] args) throws IOException {

        serverLogicProcessor = new ServerLogicProcessor();

        ServerSocket socket = new ServerSocket(port);

        ExecutorService service = Executors.newFixedThreadPool(threadPoolSize);

        System.err.println("Server is ready...");

        while (true) {
            Socket s = socket.accept();
            service.submit(new SocketProcessor(s));
            System.out.println("Port"+ s.getLocalPort()+"connected");

        }
    }
}
