package com.epam.javacore2019.steve2;

import com.epam.javacore2019.steve2.appserver.ServerApplication;
import com.epam.javacore2019.steve2.dbservice.DBApplication;
import com.epam.javacore2019.steve2.webservice.WebClientApplication;

public class MainApplication {

    public static void main(String[] args) {
        try {
            DBApplication.INSTANCE.start();
            WebClientApplication.INSTANCE.start();
            ServerApplication.INSTANCE.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
