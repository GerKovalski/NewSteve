package com.epam.javacore2019.steve2.dbservice.data.query;

import com.epam.javacore2019.steve2.dbservice.DBApplication;
import com.epam.javacore2019.steve2.dbservice.data.Table;
import com.epam.javacore2019.steve2.dbservice.data.query.QueryProcessor;
import com.epam.javacore2019.steve2.dbservice.data.query.QueryResult;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SELECTQueryProcessor implements QueryProcessor {

    public static final String OP_GROUP = "^(SELECT)";
    public static final String FLD_GROUP = "([*a-zA-Z, ]+)";
    public static final String SPACE = "([\\s]+)";
    public static final String FROM_GROUP = "(FROM)";
    public static final String TBL_GROUP = "([a-zA-Z]+)";
    public static final String WHERE_GROUP = "(where)";
    public static final String ID_GROUP = "([0-9]+)$";

    @Override
    public QueryResult process(String query) {
        Pattern pattern = Pattern.compile(OP_GROUP + SPACE + FLD_GROUP + SPACE + FROM_GROUP + SPACE + TBL_GROUP);
        QueryResult queryResult = new QueryResult();
        Matcher matcher = pattern.matcher(query);
        queryResult.status = QueryResult.Status.OK;
        if (matcher.find()) {
            String[] fields = matcher.group(3).split(",");
            String tableName = matcher.group(7);
            Table table = DBApplication.INSTANCE.getTable(tableName);
            if (table != null) {
                List<List<String>> result = table.collect(fields);
                queryResult.setMessage("COMPLETED SUCCESSFULLY");
                queryResult.setLoad(collectedResultToString(result));
            } else {
                queryResult.setStatus(QueryResult.Status.FAILURE);
                queryResult.setMessage("TABLE DOES NOT EXISTS");
            }
        } else {
            queryResult.setStatus(QueryResult.Status.FAILURE);
            queryResult.setMessage("INVALID QUERY");
        }

        return queryResult;
    }

    private String collectedResultToString(List<List<String>> collectedResult) {
        String result = "";
        for (List<String> list : collectedResult ) {
            for (String s : list) {
                result += s + ",";
            }
            result = result.substring(0,result.length()-1);
            result += ";";
        }
        return result;
    }

}
