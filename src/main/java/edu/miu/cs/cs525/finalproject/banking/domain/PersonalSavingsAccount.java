package edu.miu.cs.cs525.finalproject.banking.domain;

import edu.miu.cs.cs525.finalproject.banking.service.PersonalSavingsInterest;
import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;

public class PersonalSavingsAccount extends BankingAccount {
    public PersonalSavingsAccount(String accountNumber, String type, Customer customer) {
        super(accountNumber, type, customer);
        this.interest = new PersonalSavingsInterest();
    }

    @Override
    public void withdrawNotification(double amount) {
        if (getBalance() < 0 || amount > 500) {
            String message = "Your account has just been withdrawn $" + amount + ". The current balance is $" + getBalance() + ".";
            notifyObservers(message);
        }
    }

    @Override
    public void depositNotification(double amount) {
        if (getBalance() < 0 || amount > 500) {
            String message = "Your account has just been deposited $" + amount + ". The current balance is $" + getBalance() + ".";
            notifyObservers(message);
        }
    }
}
