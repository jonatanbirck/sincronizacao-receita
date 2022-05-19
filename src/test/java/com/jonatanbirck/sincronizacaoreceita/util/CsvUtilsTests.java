package com.jonatanbirck.sincronizacaoreceita.util;

import com.jonatanbirck.sincronizacaoreceita.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

public class CsvUtilsTests {

    @Test
    void checkIfCanCreateCsvFile() {
        //given
        String[] header = {"id, agency, number, owner, date"};
        List<String[]> data = new ArrayList<>(5);
        data.add(new String[]{"1", "7100", "650325", "Felipe", "19/05/2022"});
        data.add(new String[]{"2", "0910", "296352", "Bruno", "20/05/2022"});
        data.add(new String[]{"3", "4362", "252977", "Milena", "22/05/2022"});
        data.add(new String[]{"4", "9469", "108860", "Rodrigo", "23/05/2022"});
        data.add(new String[]{"5", "9469", "519534", "Eduarda", "25/05/2022"});

        //when
        try {
            CsvUtils.createCSV(header, data, new File(TestUtils.withBasePath("test_checkIfCanCreateCsvFile.csv")));
        } catch (IOException e) {
            fail("Exception on 'CsvUtils.createCSV'");
        }

        boolean expect = FileUtils.isCsvFile(new File(TestUtils.withBasePath("test_checkIfCanCreateCsvFile.csv")));

        //then
        assertThat(expect).isTrue();
    }

    @Test
    void checkIfCanCreateFakeCsvFile() {
        //when
        try {
            CsvUtils.createFakeCSV(150, new File(TestUtils.withBasePath("test_checkIfCanCreateFakeCsvFile.csv")));
        } catch (IOException | NoSuchAlgorithmException e) {
            fail("Exception on 'CsvUtils.createFakeCSV'");
        }

        boolean expect = FileUtils.isCsvFile(new File(TestUtils.withBasePath("test_checkIfCanCreateFakeCsvFile.csv")));

        //then
        assertThat(expect).isTrue();
    }

    @AfterEach
    void clean() {
        new File(TestUtils.withBasePath("test_checkIfCanCreateCsvFile.csv")).deleteOnExit();
        new File(TestUtils.withBasePath("test_checkIfCanCreateFakeCsvFile.csv")).deleteOnExit();
    }

}
