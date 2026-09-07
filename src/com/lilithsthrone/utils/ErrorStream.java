package com.lilithsthrone.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

import static com.lilithsthrone.main.Main.VERSION_NUMBER;

public class ErrorStream extends PrintStream {
    /* this is a size assumption on average size + mod folders */
    public static int ERR_LOG_INFO_LENGTH = 22;
    public static boolean newErrorLog = false;
    private static final ArrayList<String> KNOWN_ISSUES = new ArrayList<>();// this allows us to track what errors we've seen, to skip duplicate reports
    public static int informationLines = 1;
    public static boolean skipNextNL = false;

    public ErrorStream(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    @Override
    public void write(byte[] buf, int off, int len) {
        String str = new String(buf, StandardCharsets.UTF_8); // Enables magic
        if (str.startsWith("|") || str.startsWith("=")) informationLines++;
        if (KNOWN_ISSUES.contains(str) && !str.startsWith("=")) return; // don't break my neat box
        if (!str.equals(System.lineSeparator()))
            KNOWN_ISSUES.add(str);
        if (str.contains("WARNING: Unsupported JavaFX configuration") ||// HAHA We don't care! Java 9+ warning
                str.trim().startsWith("at com.sun.") ||
                str.trim().startsWith("at javafx.") || // added these to make the logs more readable,
                str.trim().startsWith("at java.") ||
                str.trim().startsWith("at org.openjdk.") ||
                str.trim().startsWith("at jdk.dynalink")) {
            skipNextNL = true;
            return; //we don't care where in java the issue visited, only where in code path it happened.
        }
        if (skipNextNL) {
            skipNextNL = false;
            return;
        }
        super.write(buf, off, len);// The important line.
    }

    public void printData() {
//        System.err.print("=".repeat(64) + System.lineSeparator());
        System.err.print(repeatString("=", 64) + System.lineSeparator());
        printInformationLine("Game Version : " + VERSION_NUMBER);
        printInformationLine("Java : " + System.getProperty("java.version") + " [" + System.getProperty("sun.arch.data.model") + "-bit] (" + System.getProperty("java.vendor") + ")");
        printInformationLine("OS : " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ")");
        if (new File("res/mods").exists()) {
            StringBuilder mods = new StringBuilder();
            int i = 0;
            for (File f : Objects.requireNonNull(new File("res/mods").listFiles())) {
                if (f.isDirectory()) {
                    if (i > 0) {
                        mods.append(", ");
                    }
                    mods.append(f.getName());
                }
                i++;
            }
            String info = "Mod folders present: " + mods;
            if (info.length() > 60) {
                String[] infArr = mods.toString().split(", ");
                StringBuilder tInfo = new StringBuilder();
                printInformationLine("Mod folders present");
                for (int j = 0; j < infArr.length; j++) {
                    if ((tInfo + (j == 0 ? "" : ", ") + infArr[j]).length() < 60) {
                        tInfo.append(j == 0 ? "" : ", ").append(infArr[j]);
                    } else {
                        printInformationLine(tInfo.toString());
                        tInfo.setLength(0);
                    }
                }
                printInformationLine(tInfo.toString());
            } else {
                printInformationLine(info);
            }
        }
        printInformationLine("Send this log file in a report on github");
//        System.err.print("=".repeat(64) + System.lineSeparator());
        System.err.print(repeatString("=", 64) + System.lineSeparator());
        // This is saved in a file on my build, but as this isn't mine, I'm just making an assumption on average info size
//        ERR_LOG_INFO_LENGTH = informationLines;// so we don't make backups of logs that are not longer than information chunk
    }

    public void printInformationLine(String information) {
        int l = 62 - information.length();
        System.err.print("|" + repeatString(" ", (int) (l / 2f)) + information + repeatString(" ", Math.round(l / 2f)) + "|" + System.lineSeparator());
    }

    private static String repeatString(String in, int count) {
        String out = "";
        for (int i = 0; i < count; i++) {
            out += in;
        }
        return out;
    }

    public static long getLogLengthSafe(File errorLog) {
        long len = 0;
        if (!errorLog.exists()) return len;
        try { len = getLogLength(errorLog); } catch (IOException ignored) { }
        return len;
    }

    private static long getLogLength(File errorLog) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(errorLog));
        long length = br.lines().count();
        br.close();
        return length;
    }
}