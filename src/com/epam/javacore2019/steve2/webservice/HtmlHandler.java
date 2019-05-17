package com.epam.javacore2019.steve2.webservice;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

public class HtmlHandler implements HttpHandler {

    public static final String HTML_FORMAT = "^/pages/(([a-zA-Z]+\\.)(html))$";

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().getPath();
        String response = "";
        if (path.matches(HTML_FORMAT)) {
            response = path;
        } else {
            response = "INVALID URL : " + path;
        }
        File file = new File("webclient" + path);
        response += "<br>File Exists: " + file.exists();
        byte[] fileBytes = null;
        if (file.exists()) {
            fileBytes = Utils.readBytes("webclient" + path);
        }

        //TODO - temporarily, for demo
        String result = new String(fileBytes);
        result = result.replace("{{time}}", LocalTime.now().toString());

        httpExchange.getResponseHeaders().put("Content-Type", Arrays.asList(new String[]{"text/html"}));
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        //  os.write(("Html Handler: " + response).getBytes());
        if (fileBytes != null) {
            os.write(result.getBytes());
        }
        os.close();
    }
}
