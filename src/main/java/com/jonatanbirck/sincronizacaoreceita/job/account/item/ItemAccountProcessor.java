package com.jonatanbirck.sincronizacaoreceita.job.account.item;

import com.jonatanbirck.sincronizacaoreceita.model.AccountUpdate;
import com.jonatanbirck.sincronizacaoreceita.service.mock.ReceitaService;
import lombok.AllArgsConstructor;

/**
 * Responsible for send the request to Receita Federal and write the result
 */
@AllArgsConstructor
public class ItemAccountProcessor implements Runnable {

    private AccountUpdate account;
    private ItemAccountWriter writer;

    @Override
    public void run() {
        try {
            boolean updated = new ReceitaService().atualizarConta(
                    account.getAgency(),
                    account.getNumber(),
                    account.getBalanceAsDouble(),
                    account.getStatus()
            );

            if (updated) {
                account.setResult(AccountUpdate.Status.SUCCESS);
            } else {
                account.setResult(AccountUpdate.Status.FAIL);
            }

            writer.write(account);
        } catch (RuntimeException | InterruptedException e) {
            account.setResult(AccountUpdate.Status.ERROR);
            writer.write(account);
            Thread.currentThread().interrupt();
        }
    }
}
