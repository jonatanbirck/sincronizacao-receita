package com.jonatanbirck.sincronizacaoreceita.util;

import com.jonatanbirck.sincronizacaoreceita.entity.Account;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CsvUtils {

    private CsvUtils() {}

    /**
     * Create a new file .csv
     *
     * @param header
     * @param rows
     * @param outputFile
     * @throws IOException
     */
    public static void createCSV(String[] header, List<String[]> rows, File outputFile) throws IOException {
        BufferedWriter writer = null;

        try {
            if (header == null || CollectionUtils.isEmpty(rows) || FileUtils.isCsvFile(outputFile)) {
                return;
            }

            writer = Files.newBufferedWriter(outputFile.toPath());

            final StringBuilder headerBuilder = new StringBuilder();

            for (String headerName : header) {
                headerBuilder.append(headerName).append(";");
            }

            int lenghtBuilder = headerBuilder.length();
            final String headerText = headerBuilder.replace((lenghtBuilder-1), lenghtBuilder, "\n").toString();

            writer.write(headerText);

            for (String[] row : rows) {
                final StringBuilder rowBuilder = new StringBuilder();

                for (String rowValue : row) {
                    rowBuilder.append(rowValue).append(";");
                }

                lenghtBuilder = rowBuilder.length();
                final String rowText = rowBuilder.replace((lenghtBuilder-1), lenghtBuilder, "\n").toString();

                writer.write(rowText);
                writer.flush();
            }
        } catch (Exception e) {
            //TODO: log in a better way
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    //TODO: Just for mock, remove after
    public static void createFakeCSV(int amount, File outputFile) throws NoSuchAlgorithmException, IOException {
        final String[] header = {"agencia", "conta", "saldo", "status"};
        final List<String[]> rows = new ArrayList<>();
        final Random random = SecureRandom.getInstanceStrong();

        for (int i = 0; i < amount; i++) {
            final Account accout = new Account()
                    .withAgency(randomAgency(random))
                    .withNumber(randomNumber(random))
                    .withBalance(randomBalance(random))
                    .withStatus(randomStatus(random));

            String[] row = {
                    accout.getAgencyAsString(),
                    accout.getNumberAsString(true),
                    accout.getBalanceAsString(),
                    accout.getStatus().name()
            };

            rows.add(row);
        }

        CsvUtils.createCSV(header, rows, outputFile);
    }

    //TODO: Just for mock, remove after
    private static short randomAgency(Random random) {
        return Short.parseShort(random.nextInt(9999) + "");
    }

    //TODO: Just for mock, remove after
    private static int randomNumber(Random random) {
        return random.nextInt(999999);
    }

    //TODO: Just for mock, remove after
    private static BigDecimal randomBalance(Random random) {
        boolean negative = random.nextInt(4) > 2; //25% chance of being negative
        int n1 = random.nextInt(9999);
        int n2 = random.nextInt(99);
        return new BigDecimal((negative ? "-" : "") + n1 + "." + n2);
    }

    //TODO: Just for mock, remove after
    private static Account.Status randomStatus(Random random) {
        switch (random.nextInt(3)) {
            case 0: return Account.Status.B;
            case 1: return Account.Status.A;
            case 2: return Account.Status.I;
            case 3: return Account.Status.P;
            default: return Account.Status.A;
        }
    }

}
