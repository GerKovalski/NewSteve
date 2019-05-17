package com.epam.javacore2019.steve2.dbservice.server;

import com.epam.javacore2019.steve2.dbservice.DBApplication;
import com.epam.javacore2019.steve2.dbservice.data.query.QueryResult;
import com.epam.javacore2019.steve2.dbservice.misc.DataHandler;
import com.epam.javacore2019.steve2.dbservice.misc.Utils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.List;
import java.util.Map;

public class QueryHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange request) throws IOException {
        InputStream is = request.getRequestBody();
        final DBQueryRequest dbQueryRequest = new DBQueryRequest();
        Utils.readStream(is, new DataHandler() {
            @Override
            public void handleString(String line) {
                System.out.println(String.format("DB SAYS, REQUEST BODY: %s", line));
                dbQueryRequest.setQueryString(line);
            }
        });

        QueryResult result = DBApplication.INSTANCE.query(dbQueryRequest.getQueryString());
        String response = "";
        if (result.getStatus().equals(QueryResult.Status.OK)) {
            response = (String)(result.getLoad());
        } else {
            response = result.getStatus() + "\n" + result.getMessage();
        }

        //String response = dbQueryRequest.getQueryString();
        request.sendResponseHeaders(200, response.length());
        OutputStream os = request.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static class DBQueryRequest {
        private String queryString;

        public String getQueryString() {
            return queryString;
        }

        public void setQueryString(String queryString) {
            this.queryString = queryString;
        }
    }


}
