package com.jonatanbirck.sincronizacaoreceita.config.main.task;

import com.jonatanbirck.sincronizacaoreceita.service.account.UpdateAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Responsible for updating accounts when run standalone by the application
 */
@Component
@AllArgsConstructor
public class UpdateAccountTask implements TaskExecutor<String> {

    private final UpdateAccountService service;

    @Override
    public void execute(String arg) {
        try {
            if (arg == null) return;
            final String[] argSplited = arg.split(",");
            if (argSplited.length == 1) {
                service.updateAccounts(new File(argSplited[0]));
            } else if (argSplited.length == 2) {
                service.updateAccounts(new File(argSplited[0]), Integer.parseInt(argSplited[1]));
            }
        } catch (Exception e) {
            //TODO: log in a better way
            e.printStackTrace();
        }
    }

}
