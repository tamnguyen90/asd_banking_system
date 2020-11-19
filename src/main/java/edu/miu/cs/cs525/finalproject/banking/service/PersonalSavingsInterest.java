package edu.miu.cs.cs525.finalproject.banking.service;

import edu.miu.cs.cs525.finalproject.framework.service.InterestCalculation;

public class PersonalSavingsInterest implements InterestCalculation {
    @Override
    public double calculateInterest(double balance) {
        if (balance < 1000) {
            return balance * 0.1;
        } else if ((balance > 1000) && (balance < 5000)) {
            return balance * 0.2;
        } else {
            return balance * 0.4;
        }
    }
}
