package com.netcracker.practise.eventsplanner;

import com.netcracker.practise.eventsplanner.controller.connection.ConnectionProcessor;

import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        ConnectionProcessor cp = new ConnectionProcessor();
        try {
            cp.main(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
