package com.jonatanbirck.sincronizacaoreceita.job.account.item;

import com.jonatanbirck.sincronizacaoreceita.exception.InvalidCsvFormatException;
import com.jonatanbirck.sincronizacaoreceita.job.item.ItemWriter;
import com.jonatanbirck.sincronizacaoreceita.model.AccountUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Responsible for writing the collection result file at the Receita Federal
 */
@Getter
@AllArgsConstructor
public class ItemAccountWriter implements ItemWriter<AccountUpdate> {

    private BufferedWriter writer;
    private static final String DELIMITER = ";";
    private static final int NUMBER_OF_COLUMNS = 4;
    private static final String[] HEADER = {"agencia", "conta", "saldo", "status", "resultado"};

    public ItemAccountWriter(File file) throws IOException {
        this.writer = Files.newBufferedWriter(file.toPath());
        writeLine(HEADER); //create header
    }

    @Override
    public void write(AccountUpdate accountUpdate) {
        try {
            writeLine(
                    new String[]{
                            accountUpdate.getAgency(),
                            accountUpdate.getNumber(),
                            accountUpdate.getBalance(),
                            accountUpdate.getStatus(),
                            accountUpdate.getResult().getName()
                    }
            );
        } catch (IOException e) {
            //TODO: log in a better way
            e.printStackTrace();
        }
    }

    private void writeLine(String row) throws IOException {
        writer.write(row);
    }

    private void writeLine(String[] row) throws IOException {
        if (row == null || row.length != 5) {
            throw new InvalidCsvFormatException();
        }

        writeLine(
            new StringBuilder()
                .append(row[0]).append(DELIMITER)
                .append(row[1]).append(DELIMITER)
                .append(row[2]).append(DELIMITER)
                .append(row[3]).append(DELIMITER)
                .append(row[4]).append("\n")
                .toString()
        );
    }

}
