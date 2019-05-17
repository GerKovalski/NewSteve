package com.epam.javacore2019.steve2.dbservice.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class BaseHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("BaseHandler entering: " + httpExchange.getHttpContext().getPath());
        String noWay = "NO WAY!";
        httpExchange.sendResponseHeaders(200, noWay.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write("NO WAY!".getBytes());
        httpExchange.close();
    }
}
