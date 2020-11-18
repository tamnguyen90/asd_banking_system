package edu.miu.cs.cs525.finalproject.framework.service;

import edu.miu.cs.cs525.finalproject.framework.dao.AccountDAO;
import edu.miu.cs.cs525.finalproject.framework.dao.AccountDAOImpl;
import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;

import java.util.Collection;
import java.util.List;

public abstract class AccountService {
    private AccountDAO accountDAO;
    public AccountService() {
        accountDAO = new AccountDAOImpl();
    }

    public void createAccount(Customer customer, Account account) {
        accountDAO.saveAccount(customer, account);
    }

    public Account getAccount(String accountNumber) {
        return accountDAO.loadAccount(accountNumber);
    }

    public Collection<Account> getAllAccounts() {
        return accountDAO.getAccounts();
    }

    public void deposit(String accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.deposit(amount);

        accountDAO.updateAccount(account.getCustomer().getName(), account);
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.withdraw(amount);

        accountDAO.updateAccount(account.getCustomer().getName(), account);
    }

    public void sendEmail(String accountNumber, String message) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.notifyObservers(message);
    }

    public void addInterests() {
        Collection<Account> accounts = accountDAO.getAccounts();
        for (Account account : accounts) {
            account.addInterest();
        }
    }

    public abstract String generateReport();
}
