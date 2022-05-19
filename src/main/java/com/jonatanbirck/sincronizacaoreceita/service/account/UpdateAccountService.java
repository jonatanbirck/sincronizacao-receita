package com.jonatanbirck.sincronizacaoreceita.service.account;

import com.jonatanbirck.sincronizacaoreceita.job.account.UpdateAccountJob;
import com.jonatanbirck.sincronizacaoreceita.job.account.item.ItemAccountTemplate;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class UpdateAccountService {

    public void updateAccounts(File csvFile) {
        UpdateAccountJob.execute(new ItemAccountTemplate(csvFile));
    }

    public void updateAccounts(File csvFile, int numberOfThreads) {
        UpdateAccountJob.execute(new ItemAccountTemplate(csvFile, numberOfThreads));
    }

}
