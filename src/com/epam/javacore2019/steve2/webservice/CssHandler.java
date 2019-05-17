package com.epam.javacore2019.steve2.webservice;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;

public class CssHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().getPath();
        File file = new File("webclient/static" + path);
        byte[] fileBytes = null;
        if (file.exists()) {
            fileBytes = Utils.readBytes("webclient/static" + path);
        }
        //application/javascript
        httpExchange.getResponseHeaders().put("Content-Type", Arrays.asList(new String[]{"text/css"}));
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        if (fileBytes != null) {
            os.write(fileBytes);
        }
        os.close();
    }
}
