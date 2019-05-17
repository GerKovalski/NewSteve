package com.epam.javacore2019.steve2.dbservice.data.query;

import java.util.HashMap;
import java.util.Map;

public class QueryProcessorRegister {

    static Map<String, QueryProcessor> queryProcessors;

    static {
        queryProcessors = new HashMap<>();
        queryProcessors.put("SELECT", new SELECTQueryProcessor());
        queryProcessors.put("DELETE", null);
        queryProcessors.put("UPDATE", null);
        queryProcessors.put("INSERT", null);
    }

    public static QueryProcessor getQueryProcessor(String queryType) {
        return queryProcessors.get(queryType);
    }

}
