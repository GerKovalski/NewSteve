package com.epam.javacore2019.steve2.dbservice.server;

import com.epam.javacore2019.steve2.dbservice.DBApplication;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;

public enum DBServer {
    INSTANCE;
    public static final int PORT = 6701;
    public static final String SERVER_NAME = "Steve DB Server";
    public void start() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 10 );
        server.createContext("/api/query", new QueryHandler());
        server.start();
        String message = String.format("%s is running on port: %d", SERVER_NAME, server.getAddress().getPort());
        System.out.println(message);
    }



}
