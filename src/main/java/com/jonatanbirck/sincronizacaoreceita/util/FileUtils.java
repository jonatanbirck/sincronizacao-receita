package com.jonatanbirck.sincronizacaoreceita.util;

import java.io.File;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Locale;

public class FileUtils {

    private FileUtils() {}
    private static final String EMPTY = "";

    /**
     * Exists and isFile and terminated with .csv - true
     * Otherwise is false
     *
     * @param file
     * @return boolean
     */
    public static boolean isCsvFile(File file) {
        if (!isFile(file)) return false;
        return getFileExtension(file).toUpperCase(Locale.ROOT).equals("CSV");
    }

    public static String getFileName(File file) {
        if (!isFile(file)) return EMPTY;
        final String path = file.getPath();
        final String[] fileSplited;
        if (path.contains("/")) {
            fileSplited = path.split("/");
        } else {
            fileSplited = path.split("\\\\");
        }
        final String[] nameSplited = fileSplited[fileSplited.length-1].split("\\.");
        return nameSplited[0];
    }

    public static String getFileExtension(File file) {
        if (!isFile(file)) return EMPTY;
        final String[] fileSplited = file.getPath().split("\\.");
        return fileSplited[fileSplited.length-1];
    }

    public static String getFileNameWithExtension(File file) {
        if (!isFile(file)) return EMPTY;
        final String path = file.getPath();
        String[] nameSplited;
        if (path.contains("/")) {
            nameSplited = path.split("/");
            return nameSplited[nameSplited.length-1];
        } else {
            nameSplited = path.split("\\\\");
            return nameSplited[nameSplited.length-1];
        }
    }

    private static boolean isFile(File file) {
        if (file == null) return false;
        if (!file.exists()) return false;
        return file.isFile();
    }

    public static String humanReadableByteCountSI(long bytes) {
        if (-1000 < bytes && bytes < 1000) {
            return bytes + " B";
        }
        CharacterIterator ci = new StringCharacterIterator("kMGTPE");
        while (bytes <= -999_950 || bytes >= 999_950) {
            bytes /= 1000;
            ci.next();
        }
        return String.format("%.1f %cB", bytes / 1000.0, ci.current());
    }

}
