package edu.miu.cs.cs525.finalproject.creditcard.domain;

import edu.miu.cs.cs525.finalproject.creditcard.service.SilverCreditCardAccountInterest;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;
import edu.miu.cs.cs525.finalproject.framework.domain.InterestCalculation;

import java.time.LocalDate;

public class SilverCreditCardAccount extends CreditCardAccount {
    public SilverCreditCardAccount(String accountNumber, String type, Customer customer, LocalDate expiredDate) {
        super(accountNumber, type, customer, new SilverCreditCardAccountInterest(), expiredDate);
    }
}
