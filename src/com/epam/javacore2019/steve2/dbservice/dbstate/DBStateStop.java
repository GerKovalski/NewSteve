package com.epam.javacore2019.steve2.dbservice.dbstate;

import com.epam.javacore2019.steve2.dbservice.misc.DBConstants;
import com.epam.javacore2019.steve2.dbservice.misc.Utils;

import java.util.ArrayList;
import java.util.List;

public class DBStateStop extends DBState {

    public DBStateStop(String name) {
        super(name);
    }

    @Override
    public void enter() {
        System.out.println("Entering DBStop state");

        List<String> list = new ArrayList<>();
        list.add("test string one");
        list.add("test string two");
        Utils.writeListToFile(list, DBConstants.DATA_DIR + "/test.dat");
    }

    @Override
    public void onStop() {
        System.out.println("Already trying to stop...");
    }
}
