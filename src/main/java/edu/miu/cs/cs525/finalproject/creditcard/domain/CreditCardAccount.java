package edu.miu.cs.cs525.finalproject.creditcard.domain;

import edu.miu.cs.cs525.finalproject.creditcard.service.MinimumPaymentCalculation;
import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.AccountEntry;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;

import java.time.LocalDate;

public abstract class CreditCardAccount extends Account {
    private LocalDate expiredDate;
    protected MinimumPaymentCalculation minimumPaymentCalculation;

    public CreditCardAccount(String accountNumber, String type, Customer customer, LocalDate expiredDate) {
        super(accountNumber, type, customer);
        this.expiredDate = expiredDate;
    }

    public void addPayment() {
        AccountEntry entry = new AccountEntry(-minimumPaymentCalculation.calculateMPayment(this.getBalance()), "interest adding");
        getEntryList().add(entry);
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }
}
