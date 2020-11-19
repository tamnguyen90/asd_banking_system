package edu.miu.cs.cs525.finalproject.creditcard.service;

public class SilverCreditMinimumPayment implements MinimumPaymentCalculation {
    public double calculateMPayment(double payment) {
return 0.12*payment;
    }
}
