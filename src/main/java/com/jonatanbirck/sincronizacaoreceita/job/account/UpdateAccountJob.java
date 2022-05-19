package com.jonatanbirck.sincronizacaoreceita.job.account;

import com.jonatanbirck.sincronizacaoreceita.job.account.item.ItemAccountProcessor;
import com.jonatanbirck.sincronizacaoreceita.job.account.item.ItemAccountReader;
import com.jonatanbirck.sincronizacaoreceita.job.account.item.ItemAccountTemplate;
import com.jonatanbirck.sincronizacaoreceita.job.account.item.ItemAccountWriter;
import com.jonatanbirck.sincronizacaoreceita.model.AccountUpdate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Responsible for managing account updates
 */
public class UpdateAccountJob {

    private UpdateAccountJob() {}

    public static void execute(ItemAccountTemplate template) {
        try {
            final ItemAccountReader reader = new ItemAccountReader(template.getFile());
            final ItemAccountWriter writer = new ItemAccountWriter(template.getTempFile());

            ExecutorService executorService = Executors.newFixedThreadPool(template.getNumberOfThreads());

            AccountUpdate account = reader.read();

            while (account != null) {
                executorService.execute(new ItemAccountProcessor(account, writer));
                account = reader.read();
            }

            executorService.shutdown();

            //main thread wait all processors finish their jobs
            while(!executorService.isTerminated()) {
                Thread.sleep(100);
            }

            reader.getReader().close();
            writer.getWriter().flush();
            writer.getWriter().close();


            Files.move(
                template.getTempFile().toPath(),
                template.getFile().toPath(),
                StandardCopyOption.REPLACE_EXISTING
            );

        } catch (IOException | InterruptedException e) {
            //TODO: log in a better way
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
