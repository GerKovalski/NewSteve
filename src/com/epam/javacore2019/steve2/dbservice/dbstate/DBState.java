package com.epam.javacore2019.steve2.dbservice.dbstate;

import com.epam.javacore2019.steve2.dbservice.data.query.QueryResult;

public abstract class DBState {

    protected String name;

    public DBState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void enter() {
        System.out.println("Basic entering state");
    }

    public QueryResult onQuery(String query) {
        return new QueryResult(QueryResult.Status.FAILURE, String.format("UNABLE TO PROCESS QUERY: %s", query));
    }

    public void exit() {
        System.out.println("Basic exiting state");
    }

    public void onStop() {
        System.out.println("Basic stop handling");
    }

}
