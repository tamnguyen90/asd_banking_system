package edu.miu.cs.cs525.finalproject.banking.domain;

import edu.miu.cs.cs525.finalproject.banking.service.interest.CompanySavingsInterest;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;

public class CompanySavingsAccount extends BankingAccount {
    public CompanySavingsAccount(String accountNumber, String type, Customer customer) {
        super(accountNumber, type, customer);
        this.interest = new CompanySavingsInterest();
    }
}
