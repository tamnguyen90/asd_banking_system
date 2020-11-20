package edu.miu.cs.cs525.finalproject.creditcard.domain;

import edu.miu.cs.cs525.finalproject.creditcard.service.interest.SilverCreditCardAccountInterest;
import edu.miu.cs.cs525.finalproject.creditcard.service.minimumpayment.SilverCreditMinimumPayment;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;

import java.time.LocalDate;

public class SilverCreditCardAccount extends CreditCardAccount {
    public SilverCreditCardAccount(String accountNumber, String type, Customer customer, LocalDate expiredDate) {
        super(accountNumber, type, customer, expiredDate);
        this.minimumPaymentCalculation = new SilverCreditMinimumPayment();
        this.interest = new SilverCreditCardAccountInterest();
    }
}
