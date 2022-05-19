package com.jonatanbirck.sincronizacaoreceita.config.main.task;

import com.jonatanbirck.sincronizacaoreceita.util.CsvUtils;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Responsible for creating a fake csv file when run standalone by the application
 */
@Component
public class CreateFakeCsvTask implements TaskExecutor<String> {

    @Override
    public void execute(String arg) {
        try {
            if (arg == null) return;
            String[] argSplited = arg.split(",");
            if (argSplited.length != 2) return;
            CsvUtils.createFakeCSV(Integer.parseInt(argSplited[0]), new File(argSplited[1]));
        } catch (Exception e) {
            //TODO: log in a better way
            e.printStackTrace();
        }
    }

}
