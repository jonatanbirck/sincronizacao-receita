package com.jonatanbirck.sincronizacaoreceita.job.account.item;

import com.jonatanbirck.sincronizacaoreceita.util.FileUtils;
import lombok.Getter;

import java.io.File;

/**
 * Represents job entries and settings
 */
@Getter
public class ItemAccountTemplate {

    private final File file;
    private final File tempFile;
    private int numberOfThreads = Runtime.getRuntime().availableProcessors() * 4;

    public ItemAccountTemplate(File file) {
        this.file = file;
        final String name = FileUtils.getFileName(file);
        final String extension = FileUtils.getFileExtension(file);
        final String fileName = name + "." + extension;
        final String tempFileName = name + "_temp." + extension;
        this.tempFile = new File(file.getPath().replace(fileName, tempFileName));
    }

    public ItemAccountTemplate(File file, int numberOfThreads) {
        this(file);
        this.numberOfThreads = numberOfThreads;
    }

}
