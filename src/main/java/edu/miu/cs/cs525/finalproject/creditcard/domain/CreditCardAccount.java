package edu.miu.cs.cs525.finalproject.creditcard.domain;

import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;
import edu.miu.cs.cs525.finalproject.framework.domain.InterestCalculation;

import java.time.LocalDate;

public abstract class CreditCardAccount extends Account {
    private LocalDate expiredDate;

    public CreditCardAccount(String accountNumber, String type, Customer customer, InterestCalculation interest, LocalDate expiredDate) {
        super(accountNumber, type, customer, interest);
        this.expiredDate = expiredDate;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }
}
