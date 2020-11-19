package edu.miu.cs.cs525.finalproject.banking.domain;

import edu.miu.cs.cs525.finalproject.banking.service.CompanySavingsInterest;
import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;

public class CompanyCheckingsAccount extends Account {
    public CompanyCheckingsAccount(String accountNumber, String type, Customer customer) {
        super(accountNumber, type, customer, new CompanySavingsInterest());
    }
}
