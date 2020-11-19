package edu.miu.cs.cs525.finalproject.creditcard.service;

import edu.miu.cs.cs525.finalproject.framework.service.InterestCalculation;

public class BronzeCreditCardAccountInterest implements InterestCalculation {
    @Override
    public double calculateInterest(double balance) {
        return balance*0.1;
    }
}
