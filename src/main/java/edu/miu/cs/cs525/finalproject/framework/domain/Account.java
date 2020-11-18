package edu.miu.cs.cs525.finalproject.framework.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Account implements Observable{
    private Customer customer;
    private InterestCalculation interest;
    private String accountNumber;
    private String type;
    private List<AccountEntry> entryList;
    private ArrayList<Observer> observers;

    public Account(String accountNumber, String type, Customer customer, InterestCalculation interest) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.customer = customer;
        this.interest = interest;
        this.observers = new ArrayList<Observer>();
        this.entryList = new ArrayList<AccountEntry>();
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

    public void notifyObservers(String message) {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(message);
        }
    }

    public void accountChanged(String message) {
        notifyObservers(message);
    }

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
        String message = "Your account has just been deposited $" + amount + ". The current balance is $" + getBalance() + ".";
        notifyObservers(message);
    }

    public void withdraw(double amount) {
        String transactionType = "credit".equals(type) ? "charged" : "withdrawn";
        AccountEntry entry = new AccountEntry(-amount, transactionType);
        entryList.add(entry);
        String message = "Your account has just been " + transactionType + " $" + amount + ". The current balance is $" + getBalance() + ".";
        notifyObservers(message);
    }

    private void addEntry(AccountEntry entry) {
        entryList.add(entry);
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

    public String getType() {
        return type;
    }

}
