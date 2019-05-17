package com.epam.javacore2019.steve2.dbservice.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class QueryEditorHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange request) throws IOException {
        String response =
        "<html>" +
            "<head></head>" +
            "<body>" +
                "<form method=\"post\" action=\"/dbservice/query\" id=\"query\">" +
                    "<input name=\"query_text\" type=\"text\"></input>" +
                    "<input type=\"submit\">" +
                "</form>" +
            "</body>" +
        "</html>";
        request.sendResponseHeaders(200, response.length());
        request.getResponseHeaders().put("Content-Type", Arrays.asList(new String[]{"text/html"}));
        OutputStream os = request.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
