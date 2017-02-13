package com.netcracker.practise.eventsplanner.connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionProcessor {

    private Server server;

    private static class SocketProcessor implements Runnable {

        private Socket clientSocket;
        private InputStream input;
        private OutputStream output;
        private int id;

        private SocketProcessor(Socket s, int id) throws IOException {
            this.clientSocket = s;
            this.input = s.getInputStream();
            this.output = s.getOutputStream();
            this.id = id;
        }

        public void run() {
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket socket = new ServerSocket(7777);

        List<Thread> connections = new ArrayList<Thread>();

        while (true) {
            Socket s = socket.accept();
            SocketProcessor sp = new SocketProcessor(s, connections.size());
            Thread t = new Thread(sp);
            connections.add(t);
            t.start();
        }
    }
}
