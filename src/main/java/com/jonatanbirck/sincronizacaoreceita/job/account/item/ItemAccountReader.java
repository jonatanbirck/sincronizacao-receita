package com.jonatanbirck.sincronizacaoreceita.job.account.item;

import com.jonatanbirck.sincronizacaoreceita.exception.InvalidCsvFormatException;
import com.jonatanbirck.sincronizacaoreceita.job.item.ItemReader;
import com.jonatanbirck.sincronizacaoreceita.model.AccountUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Responsible for read the file to import in Receita Federal
 */
@Getter
@AllArgsConstructor
public class ItemAccountReader implements ItemReader<AccountUpdate> {

    private BufferedReader reader;
    private static final String DELIMITER = ";";
    private static final int NUMBER_OF_COLUMNS = 4;

    public ItemAccountReader(File file) throws IOException {
        this.reader = Files.newBufferedReader(file.toPath());
        readLine(); //skip header
    }

    @Override
    public AccountUpdate read() {
        try {
            final String line = readLine();

            if (line == null || line.isBlank()) return null;

            String[] info = line.replace("-","").split(DELIMITER);

            if (info.length != 4) {
                throw new InvalidCsvFormatException();
            }

            return new AccountUpdate(info[0], info[1], info[2], info[3]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String readLine() throws IOException {
        return reader.readLine();
    }

}
