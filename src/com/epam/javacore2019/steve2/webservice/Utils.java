package com.epam.javacore2019.steve2.webservice;

import java.io.FileInputStream;

public class Utils {

    public static byte[] readBytes(String fileName) {
        byte[] result = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            result = new byte[fis.available()];
            fis.read(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
