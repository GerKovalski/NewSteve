package com.epam.javacore2019.steve2.appserver;

import com.epam.javacore2019.steve2.webservice.*;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public enum ServerApplication  {
    INSTANCE;

    public static final int PORT = 6702;
    public static final String APP_NAME = "Application Service";

    public void start() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 10 );
        server.createContext("/api/criminals", new CriminalsApiHandler()); //criminals/{id}
        server.start();
        String message = String.format("%s is running on port: %d", APP_NAME, server.getAddress().getPort());
        System.out.println(message);
    }
}
