package edu.miu.cs.cs525.finalproject.banking.domain;

import edu.miu.cs.cs525.finalproject.banking.service.PersonalCheckingsInterest;
import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;

public class PersonalCheckingsAccount extends Account {
    public PersonalCheckingsAccount(String accountNumber, String type, Customer customer) {
        super(accountNumber, type, customer);
        this.interest = new PersonalCheckingsInterest();
    }
}
