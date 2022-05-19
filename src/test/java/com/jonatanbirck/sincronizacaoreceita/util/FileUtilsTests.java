package com.jonatanbirck.sincronizacaoreceita.util;

import com.jonatanbirck.sincronizacaoreceita.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileUtilsTests {

    @Test
    void checkIfIsCsvFile() {
        //given
        final File csvFile = new File(TestUtils.withBasePath("example.csv"));

        //when
        final boolean expect = FileUtils.isCsvFile(csvFile);

        //then
        assertThat(expect).isTrue();
    }

    @Test
    void checkIfNotExistsCsvFile() {
        //given
        final File csvFile = new File(TestUtils.withBasePath("exampleNotExists.csv"));

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
        final File csvFile = new File(TestUtils.BASE_PATH.toUri());

        //when
        final boolean expect = FileUtils.isCsvFile(csvFile);

        //then
        assertThat(expect).isFalse();
    }

}
