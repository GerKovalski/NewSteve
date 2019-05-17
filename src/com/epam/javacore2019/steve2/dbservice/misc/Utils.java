package com.epam.javacore2019.steve2.dbservice.misc;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.List;

public class Utils {

    public static void readStream(final InputStream inputStream, DataHandler handler) {
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = br.readLine()) != null) {
                handler.handleString(line);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] readBytes(String fileName) {
        BufferedReader br;
        FileInputStream fis = null;
        byte[] b = null;
        try {
            fis = new FileInputStream(fileName);
            b = new byte[fis.available()];
            fis.read(b);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    public static void readFileLineByLine(String fileName, DataHandler handler) {
        FileInputStream fis = null;
        BufferedReader br;
        try {
            fis = new FileInputStream(fileName);
            br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                handler.handleString(line);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readDir(final File dir, DataHandler handler) {
        for (final File file : dir.listFiles()) {
            if (file.isDirectory()) {
                readDir(file, handler);
            } else {
                handler.handleFile(file.getPath());
            }
        }
    }

    public static void readDir(String pathToDir, DataHandler handler) {
        final File dir = new File(pathToDir);
        readDir(dir, handler);
    }

    public static void writeListToFile(List<String> list, String filePath) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(filePath);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (String line: list) {
                bw.newLine();
                bw.write(line);
            }
            bw.flush();
            bw.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readXMLDocument(String fileName, XMLDocumentHandler handler) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        File file = new File(fileName);
        Document doc = builder.parse(file);
        handler.handleDocument(doc);
    }
}
