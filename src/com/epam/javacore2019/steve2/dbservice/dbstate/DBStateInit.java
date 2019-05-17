package com.epam.javacore2019.steve2.dbservice.dbstate;

import com.epam.javacore2019.steve2.dbservice.DBApplication;
import com.epam.javacore2019.steve2.dbservice.misc.DBConstants;
import com.epam.javacore2019.steve2.dbservice.misc.Utils;
import com.epam.javacore2019.steve2.dbservice.misc.DataHandler;
import com.epam.javacore2019.steve2.dbservice.data.Table;
import com.epam.javacore2019.steve2.dbservice.data.TableMetaData;
import com.epam.javacore2019.steve2.dbservice.server.DBServer;

public class DBStateInit extends DBState {


    public DBStateInit(String name) {
        super(name);
    }

    @Override
    public void enter() {
        System.out.println("Entering DBInit state");
        initTables();
        try {
            DBServer.INSTANCE.start();
            DBApplication.INSTANCE.changeState(DBApplication.INSTANCE.stateRun);
        } catch (Exception e) {
            e.printStackTrace();
            DBApplication.INSTANCE.changeState(DBApplication.INSTANCE.stateStop);
        }
    }

    private void initTables() {
        Utils.readDir(DBConstants.TABLE_DIR, new DataHandler() {
            @Override
            public void handleFile(String filePath) {
                TableMetaData metaData = TableMetaData.loadFromFile(filePath);
                Table table = new Table(metaData);
                table.load();
                DBApplication.INSTANCE.addTable(metaData.getTableName(), table);
            }
        });
    }

    @Override
    public void onStop() {
        //check if everything is ok
        DBApplication.INSTANCE.changeState(DBApplication.INSTANCE.stateStop);
    }
}
