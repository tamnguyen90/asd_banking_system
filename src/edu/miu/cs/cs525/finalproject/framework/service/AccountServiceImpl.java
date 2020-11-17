package edu.miu.cs.cs525.finalproject.framework.service;

import edu.miu.cs.cs525.finalproject.framework.dao.AccountDAO;
import edu.miu.cs.cs525.finalproject.framework.dao.AccountDAOImpl;
import edu.miu.cs.cs525.finalproject.framework.domain.Account;

import java.util.Collection;

public class AccountServiceImpl implements AccountService {
    private AccountDAO accountDAO;
    public AccountServiceImpl() {
        accountDAO = new AccountDAOImpl();
    }

    @Override
    public Account createAccount(String accountNumber, String customerName, String type) {
        return null;
    }

    @Override
    public Account getAccount(String accountNumber) {
        Account account = accountDAO.loadAccount(accountNumber);
        return account;
    }

    @Override
    public Collection<Account> getAllAccounts() {
        return accountDAO.getAccounts();
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.deposit(amount);

        accountDAO.updateAccount(account);
        account.accountChanged(amount);
    }

    @Override
    public void addInterest() {

    }
}
