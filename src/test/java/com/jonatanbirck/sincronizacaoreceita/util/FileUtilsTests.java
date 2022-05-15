package com.jonatanbirck.sincronizacaoreceita.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileUtilsTests {

    private static final Path basePath = Path.of("src","test","resources");

    private static URI withBasePath(String path) {
        return basePath.resolve(path).toUri();
    }

    @Test
    void checkIfIsCsvFile() {
        //given
        final File csvFile = new File(withBasePath("example.csv"));

        //when
        final boolean expect = FileUtils.isCsvFile(csvFile);

        //then
        assertThat(expect).isTrue();
    }

    @Test
    void checkIfNotExistsCsvFile() {
        //given
        final File csvFile = new File(withBasePath("exampleNotExists.csv"));

        //when
        final boolean expect = FileUtils.isCsvFile(csvFile);

        //then
        assertThat(expect).isFalse();
    }

    @Test
    void checkIfCsvFileIsNull() {
        //given
        final File csvFile = null;

        //when
        final boolean expect = FileUtils.isCsvFile(csvFile);

        //then
        assertThat(expect).isFalse();
    }

    @Test
    void checkIfCsvFileIsDirectory() {
        //given
        final File csvFile = new File(basePath.toUri());

        //when
        final boolean expect = FileUtils.isCsvFile(csvFile);

        //then
        assertThat(expect).isFalse();
    }

}
