package edu.miu.cs.cs525.finalproject.framework.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Account implements Observable{
    private Customer customer;
    private InterestCalculation interest;
    private String accountNumber;
    private List<AccountEntry> entryList = new ArrayList<AccountEntry>();
    private ArrayList<Observer> observers;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        observers = new ArrayList<Observer>();
    }

    // ==========================================================
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    public void notifyObservers(double change) {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(change);
        }
    }

    public void accountChanged(double change) {
        notifyObservers(change);
    }

// ================================================================

    public void setInterest(InterestCalculation intrst) {
        interest = intrst;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void addInterest() {
        AccountEntry entry = new AccountEntry(interest.calculateInterest(), "interest");
        entryList.add(entry);
    }

    public double getBalance() {
        double balance = 0;
        for (AccountEntry entry : entryList) {
            balance += entry.getAmount();
        }
        return balance;
    }

    public void deposit(double amount) {
        AccountEntry entry = new AccountEntry(amount, "deposit");
        entryList.add(entry);

    }

    public void withdraw(double amount) {
        AccountEntry entry = new AccountEntry(-amount, "withdraw");
        entryList.add(entry);

    }

    private void addEntry(AccountEntry entry) {
        entryList.add(entry);
    }

    public void transferFunds(Account toAccount, double amount, String description) {
        AccountEntry fromEntry = new AccountEntry(-amount, description);
        AccountEntry toEntry = new AccountEntry(amount, description);

        entryList.add(fromEntry);

        toAccount.addEntry(toEntry);

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Collection<AccountEntry> getEntryList() {
        return entryList;
    }

}
