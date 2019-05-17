package com.epam.javacore2019.steve2.dbservice.bad;

public class BadClass {
    private final String name;

    public BadClass(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public synchronized void readDataFromAnotherObject(BadClass another) {
        System.out.format("\nUpdating data in %s reading data from %s object\n", getName(), another.getName());
        another.updateDataInAnotherObject(this);
    }

    public synchronized void updateDataInAnotherObject(BadClass another) {
        System.out.format("\nLet's now update data in %s using data from %s\n", another.getName(), getName());
    }

}
