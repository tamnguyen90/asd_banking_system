package edu.miu.cs.cs525.finalproject.framework.domain;

import edu.miu.cs.cs525.finalproject.framework.service.InterestCalculation;
import edu.miu.cs.cs525.finalproject.framework.service.EmailSender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Account implements Observable{
    private Customer customer;
    protected InterestCalculation interest;
    private String accountNumber;
    private String type;
    private List<AccountEntry> entryList;
    private ArrayList<Observer> observers;

    public Account(String accountNumber, String type, Customer customer) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.customer = customer;
        this.observers = new ArrayList<Observer>();
        this.entryList = new ArrayList<AccountEntry>();
        new EmailSender(this);
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
        AccountEntry entry = new AccountEntry(interest.calculateInterest(getBalance()), "interest");
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
        depositNotification(amount);
    }

    public void withdraw(double amount) {
        AccountEntry entry = new AccountEntry(-amount, "withdraw");
        entryList.add(entry);
        withdrawNotification(amount);
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

    protected abstract void depositNotification(double amount);
    protected abstract void withdrawNotification(double amount);
    public abstract String generateReport();

}
