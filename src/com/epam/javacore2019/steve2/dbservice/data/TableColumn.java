package com.epam.javacore2019.steve2.dbservice.data;

public class TableColumn {
    private String name;
    private String displayName;

    public TableColumn(String name) {
        this.name = name;
    }

    public TableColumn(String name, String displayName) {
        this(name);
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return name;
    }
}
