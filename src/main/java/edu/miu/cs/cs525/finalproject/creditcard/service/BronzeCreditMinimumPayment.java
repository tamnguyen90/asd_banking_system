package edu.miu.cs.cs525.finalproject.creditcard.service;

public class BronzeCreditMinimumPayment implements MinimumPaymentCalculation {
    public double  calculateMPayment(double payment) {
        return 0.14*payment;
    }
}
