package com.epam.javacore2019.steve2.webservice;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class ApiPostTestHandler implements HttpHandler{
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        //received from client-browser
        InputStream is = httpExchange.getRequestBody();
        byte[] requestBytes = new byte[is.available()];
        is.read(requestBytes);
        //sending result
        httpExchange.getResponseHeaders().put("Content-Type", Arrays.asList(new String[]{"application/json"}));
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.write(requestBytes);
        os.close();
    }
}
