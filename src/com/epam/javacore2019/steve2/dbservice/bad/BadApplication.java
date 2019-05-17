package com.epam.javacore2019.steve2.dbservice.bad;

import com.epam.javacore2019.steve2.dbservice.bad.BadClass;

public class BadApplication {

    //ABOUT LAST ID;

    public void start() {

        final BadClass criminals = new BadClass("Criminals");

        final BadClass crimeFamily = new BadClass("Crime Family");

        new Thread(new Runnable() {
            public void run() { criminals.readDataFromAnotherObject(crimeFamily); }
        }).start();

        new Thread(new Runnable() {
            public void run() { crimeFamily.readDataFromAnotherObject(criminals); }
        }).start();

       // startTimer();
    }

    public void startTimer() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                        System.out.println("waiting....");
                    } catch (Exception e) {

                    }
                }
            }
        }).start();
    }

}
