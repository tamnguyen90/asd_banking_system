package edu.miu.cs.cs525.finalproject.framework.service;

import edu.miu.cs.cs525.finalproject.framework.domain.Account;

import java.util.Collection;

public interface AccountService {
    Account createAccount(String accountNumber, String customerName, String type);

    Account getAccount(String accountNumber);

    Collection<Account> getAllAccounts();

    void deposit(String accountNumber, double amount);

    void addInterest();
}
