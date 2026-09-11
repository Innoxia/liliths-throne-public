package com.lilithsthrone.threading;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;

/**
 * @since 0.4.10.7
 * @version 0.4.10.7
 * @author KeldonSlayer (DrZed)
 */
public class DocBuilders {
    private static final ArrayList<DocumentBuilderFactory> docFactories = new ArrayList<>();
    private static final ArrayList<DocumentBuilder> docBuilders = new ArrayList<>();
    /*Index variable for last DocBuilder served */
    private static int docBuilderIndex = 0;

    /* A wrapper function that obscures the try/catch allowing for try/catch removal in many places
    * While I didn't remove the try/catches in most locations (to prevent the PR from being too big)
    * It is now possible in many places, try/catch prevents JIT optimization, so usage should be minimized
    */
    public static Document parseDoc(File xmlFile) {
        Document doc;
        do {
            doc = parseResetGet(xmlFile);
        } while (doc == null);
        return doc;
    }

    public static Document parseString(String str) {
        Document doc;
        do {
            doc = parseResetGet(str);
        } while (doc == null);
        return doc;
    }

    /* A try/catch wrapper function that attempts or fails */
    private static Document parseResetGet(File xmlFile) {
        if (!xmlFile.exists()) { System.out.println("File doesn't exist : " + xmlFile.getPath()); return null; }
        Document doc;
        try {
            DocumentBuilder db = getNextDocBuilder();
            doc = db.parse(xmlFile);
            db.reset();
        } catch (Exception ignored) { return null; }// Exception Thrown is a Concurrency Issue
        return doc;
    }

    private static Document parseResetGet(String str) {
        Document doc;
        try {
            DocumentBuilder db = getNextDocBuilder();
            doc = db.parse(new ByteArrayInputStream(str.getBytes()));
            db.reset();
        } catch (Exception ignored) { return null; }// Exception Thrown is a Concurrency Issue
        return doc;
    }

    /* This produces [7] default builders to be used when they're available,
        I settled on 7 as a balance of RAM usage, and thread usage */
    private static void initBuilders() {
        for (int i = 0; i < 7; i++)
            docFactories.add(DocumentBuilderFactory.newInstance());
        try {
            for (DocumentBuilderFactory docFactory : docFactories)
                docBuilders.add(docFactory.newDocumentBuilder());
        } catch (Exception ignored) {}
    }

    /* Fetches a DocBuilder from the pool looping back at max size, in case the amount gets adjusted later */
    public static DocumentBuilder getNextDocBuilder() {
        if (docFactories.isEmpty())
            initBuilders();
        docBuilderIndex++;
        if (docBuilderIndex >= docBuilders.size())
            docBuilderIndex = 0;
        return docBuilders.get(docBuilderIndex);
    }
}