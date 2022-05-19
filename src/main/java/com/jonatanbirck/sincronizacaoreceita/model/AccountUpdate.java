package com.jonatanbirck.sincronizacaoreceita.model;

import com.jonatanbirck.sincronizacaoreceita.entity.Account;
import lombok.*;

import java.math.BigDecimal;

@With
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdate {

    private String agency;
    private String number;
    private String balance;
    private String status;
    private Status result;

    public AccountUpdate(String agency, String number, String balance, String status) {
        this.agency = agency;
        this.number = number;
        this.balance = balance;
        this.status = status;
    }

    @Getter
    @AllArgsConstructor
    public enum Status {
        ERROR("Error", "receita federal return an error"),
        FAIL("Fail", "receita federal did not update the account"),
        SUCCESS("Success", "receita federal updated the account")
        ;

        private String name;
        private String description;
    }

    /* Getters */

    public double getBalanceAsDouble() {
        return Double.parseDouble(balance.replace(",", "."));
    }

    /* Business logic */

    public Account toAccount() {
        return new Account()
                .withAgency(Short.parseShort(agency))
                .withNumber(Integer.parseInt(number.replace("-", "")))
                .withBalance(new BigDecimal(balance.replace(",", ".")))
                .withStatus(Account.Status.valueOf(status));
    }

}
