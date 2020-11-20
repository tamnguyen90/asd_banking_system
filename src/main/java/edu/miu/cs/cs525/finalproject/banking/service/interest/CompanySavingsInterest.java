package edu.miu.cs.cs525.finalproject.banking.service.interest;

import edu.miu.cs.cs525.finalproject.framework.service.InterestCalculation;

public class CompanySavingsInterest implements InterestCalculation {
    @Override
    public double calculateInterest(double balance) {
        if (balance < 1000) {
            return balance * 0.01;
        } else if ((balance > 1000) && (balance < 5000)) {
            return balance * 0.02;
        } else {
            return balance * 0.04;
        }
    }
}
