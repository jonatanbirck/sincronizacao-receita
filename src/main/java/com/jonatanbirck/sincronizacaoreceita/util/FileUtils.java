package com.jonatanbirck.sincronizacaoreceita.util;

import java.io.File;
import java.util.Locale;

public class FileUtils {

    private FileUtils() {}

    public static boolean isCsvFile(File file) {
        if (file == null) return false;
        if (!file.exists()) return false;
        if (!file.isFile()) return false;

        final String ext[] = file.getPath().split("\\.");
        return ext[ext.length-1].toUpperCase(Locale.ROOT).equals("CSV");
    }

}
