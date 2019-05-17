package com.epam.javacore2019.steve2.dbservice.data.query;

public class QueryResult {
    Status status;
    String message;
    private Object load;

    public QueryResult() {
        status = Status.FAILURE;
        message = "NO MESSAGE";
    }

    public QueryResult(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public enum Status {
        OK, FAILURE;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getLoad() {
        return load;
    }

    public void setLoad(Object load) {
        this.load = load;
    }
}
