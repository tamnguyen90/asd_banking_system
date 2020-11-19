package edu.miu.cs.cs525.finalproject.creditcard.domain;

import edu.miu.cs.cs525.finalproject.creditcard.service.BronzeCreditCardAccountInterest;
import edu.miu.cs.cs525.finalproject.creditcard.service.BronzeCreditMinimumPayment;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;

import java.time.LocalDate;

public class BronzeCreditCardAccount extends CreditCardAccount {
    public BronzeCreditCardAccount(String accountNumber, String type, Customer customer, LocalDate expiredDate) {
        super(accountNumber, type, customer, expiredDate);
        this.minimumPaymentCalculation = new BronzeCreditMinimumPayment();
        this.interest = new BronzeCreditCardAccountInterest();
    }
}
