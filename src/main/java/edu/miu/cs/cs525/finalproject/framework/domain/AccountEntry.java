package edu.miu.cs.cs525.finalproject.framework.domain;

import java.time.LocalDate;

public class AccountEntry {
    private LocalDate date;
    private double amount;
    private String description;

    public AccountEntry() {
    }

    public AccountEntry(double amount, String description) {
        super();
        this.date = LocalDate.now();
        this.amount = amount;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }


}
