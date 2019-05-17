package com.epam.javacore2019.steve2.dbservice;

import com.epam.javacore2019.steve2.dbservice.data.Table;
import com.epam.javacore2019.steve2.dbservice.data.query.QueryResult;
import com.epam.javacore2019.steve2.dbservice.dbstate.DBState;
import com.epam.javacore2019.steve2.dbservice.dbstate.DBStateInit;
import com.epam.javacore2019.steve2.dbservice.dbstate.DBStateRunning;
import com.epam.javacore2019.steve2.dbservice.dbstate.DBStateStop;

import java.util.HashMap;
import java.util.Map;

public enum DBApplication {
    INSTANCE;

    private Map<String, Table> tables = new HashMap<>();

    public static final String DATA_ENCRYPTION_LEVEL = "LOW";
    private DBState currentState;
    public DBState stateInit = new DBStateInit("Initializing");
    public DBState stateRun = new DBStateRunning("Running");
    public DBState stateStop = new DBStateStop("Shutting Down");

    public void start() {
        changeState(stateInit);
    }

    public void stop() {
        currentState.onStop();
    }

    public QueryResult query(String query) {
        return currentState.onQuery(query);
    }

    public String getStateName() {
        return currentState.getName();
    }

    public void changeState(DBState state) {
        if (currentState != null) {
            if (currentState.equals(state)) {
                return;
            } else {
                currentState.exit();
            }
        }
        currentState = state;
        currentState.enter();
    }

    public void addTable(String tableName, Table table) {
        tables.put(tableName, table);
    }

    public Table getTable(String tableName) {
        return tables.get(tableName);
    }

}
