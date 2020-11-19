package edu.miu.cs.cs525.finalproject.creditcard.service;

public class GoldCreditMinimumPayment implements MinimumPaymentCalculation {
    public double calculateMPayment(double payment) {
return 0.1*payment;
    }
}
