package com.epam.javacore2019.steve2.dbservice.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableRow {

    private List<String> values;
    //private Table table;

    public TableRow() {
        values = new ArrayList<>();
    };

    public void setValues(String rawString) {
        this.values.clear();
        String[] newValues = rawString.split(";");
        this.values.addAll(Arrays.asList(rawString.split(";")));
    }

    public void setValues(List<String> values) {
        this.values.clear();
        this.values.addAll(values);
    }

    public void setValues(String[] values) {
        this.values = Arrays.asList(values);
    }

    public List<String> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "TableRow{" +
                "values=" + values +
                '}';
    }
}
