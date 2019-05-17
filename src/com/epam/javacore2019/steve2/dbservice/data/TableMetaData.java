package com.epam.javacore2019.steve2.dbservice.data;

import com.epam.javacore2019.steve2.dbservice.misc.DataHandler;
import com.epam.javacore2019.steve2.dbservice.misc.Utils;
import com.epam.javacore2019.steve2.dbservice.misc.DBConstants;

import java.util.ArrayList;
import java.util.List;

public class TableMetaData {

    private static final String CFG_TABLE_NAME_OPTION = "name";
    private static final String CFG_TABLE_COLUMNS_OPTION = "columns";
    private static final String CFG_OPTION_SPLITTER = ": ";
    private static final String CFG_COLUMNS_SPLITTER = ";";

    private String pathToStructure;
    private String pathToData;
    private List<TableColumn> columns;
    private String tableName;

    public TableMetaData() {
        columns = new ArrayList<>();
    }

    public static TableMetaData loadFromFile(String fileName) {
        TableMetaData metaData = new TableMetaData();
        Utils.readFileLineByLine(fileName, new DataHandler() {
            @Override
            public void handleString(String line) {
                //TODO - refactor  handling
                String[] parts = line.split(CFG_OPTION_SPLITTER);
                if (parts[0].equals(CFG_TABLE_NAME_OPTION)) {
                    metaData.setTableName(parts[1]);
                }
                if (parts[0].equals(CFG_TABLE_COLUMNS_OPTION)) {
                    metaData.addColumns(parts[1]);
                }
            }
        });
        metaData.setPathToStructure(fileName);
        metaData.setPathToData(
            DBConstants.DATA_DIR + "/" +
            metaData.getTableName() +
            DBConstants.DATA_EXT)
        ;
        return metaData;
    }

    public void addColumns(String columnString) {
        for (String columnName : columnString.split(CFG_COLUMNS_SPLITTER)) {
            addColumn(new TableColumn(columnName));
        }
    }

    public int getColumnIndex(String columnName) {
        for (TableColumn c : columns) {
            if (c.getName().equals(columnName)) {
                return columns.indexOf(c);
            }
        }
        return -1;
    }

    public void addColumn(TableColumn column) {
        this.columns.add(column);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPathToData() {
        return pathToData;
    }

    public void setPathToData(String pathToData) {
        this.pathToData = pathToData;
    }

    public String getPathToStructure() {
        return pathToStructure;
    }

    public void setPathToStructure(String pathToStructure) {
        this.pathToStructure = pathToStructure;
    }

    public List<TableColumn> getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        return "TableMetaData{" +
            "pathToStructure='" + pathToStructure + '\'' +
            ", pathToData='" + pathToData + '\'' +
            ", columns=" + columns +
            ", tableName='" + tableName + '\'' +
            '}';
    }
}
