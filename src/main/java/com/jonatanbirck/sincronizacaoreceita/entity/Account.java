package com.jonatanbirck.sincronizacaoreceita.entity;

import lombok.*;

import java.math.BigDecimal;

@With
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private short agency;
    private int number;
    private BigDecimal balance;
    private Status status;

    @Getter
    @AllArgsConstructor
    public enum Status {
        A("active"),
        I("inactive"),
        P("protected"),
        B("blocked");

        private String description;
    }

    /* Getters */

    public String getAgencyAsString() {
        StringBuilder agencyBuilder = new StringBuilder(String.valueOf(agency));
        while (agencyBuilder.length() < 4) {
            agencyBuilder.insert(0, "0");
        }
        return agencyBuilder.toString();
    }

    public String getNumberAsString(boolean withSeparator) {
        StringBuilder numberBuilder = new StringBuilder(String.valueOf(number));
        while (numberBuilder.length() < 6) {
            numberBuilder.insert(0, "0");
        }
        if (withSeparator) {
            return numberBuilder.insert(5, '-').toString();
        }
        return numberBuilder.toString();
    }

    public double getBalanceAsDouble() {
        return balance.doubleValue();
    }

    public String getBalanceAsString() {
        String balanceAsString = balance.toString().replace(".", ",");
        if (balanceAsString.split(",")[1].length() == 1) {
            balanceAsString += "0";
        }
        return balanceAsString;
    }

}
