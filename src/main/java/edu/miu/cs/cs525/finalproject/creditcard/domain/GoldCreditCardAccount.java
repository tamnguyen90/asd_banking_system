package edu.miu.cs.cs525.finalproject.creditcard.domain;

import edu.miu.cs.cs525.finalproject.creditcard.service.interest.GoldCreditCardAccountInterest;
import edu.miu.cs.cs525.finalproject.creditcard.service.minimumpayment.GoldCreditMinimumPayment;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;

import java.time.LocalDate;

public class GoldCreditCardAccount extends CreditCardAccount {
    public GoldCreditCardAccount(String accountNumber, String type, Customer customer, LocalDate expiredDate) {
        super(accountNumber, type, customer, expiredDate);
        this.minimumPaymentCalculation = new GoldCreditMinimumPayment();
        this.interest = new GoldCreditCardAccountInterest();
    }
}
