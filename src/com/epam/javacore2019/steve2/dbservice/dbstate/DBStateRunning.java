package com.epam.javacore2019.steve2.dbservice.dbstate;

import com.epam.javacore2019.steve2.dbservice.DBApplication;
import com.epam.javacore2019.steve2.dbservice.data.query.QueryProcessor;
import com.epam.javacore2019.steve2.dbservice.data.query.QueryProcessorRegister;
import com.epam.javacore2019.steve2.dbservice.data.query.QueryResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBStateRunning extends DBState {

    public static final String QUERY_REGEX = "^(SELECT|UPDATE|INSERT|DELETE)([a-zA-Z1-9, ]+)$";

    public DBStateRunning(String name) {
        super(name);
    }

    @Override
    public void enter() {
        System.out.println("Entering DBRunning state");
    }

    @Override
    public void onStop() {
        //check if everything is ok
        DBApplication.INSTANCE.changeState(DBApplication.INSTANCE.stateStop);
    }

    @Override
    public QueryResult onQuery(String query) {
        Pattern pattern = Pattern.compile(QUERY_REGEX);
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            String queryType = matcher.group(1);
            QueryProcessor processor = QueryProcessorRegister.getQueryProcessor(queryType);
            if (processor != null) {
                QueryResult result = processor.process(query);
                if (result.getStatus().equals(QueryResult.Status.OK)) {
                    System.out.println("\nQUERY: " + query);
                    System.out.println("\nSTATUS: " + result.getStatus());
                    System.out.println("\nMESSAGE: " + result.getMessage());
                    System.out.println("\nDATA: " + result.getLoad());
                    return result;
                }
            }
        }
        return null;
    }
}
