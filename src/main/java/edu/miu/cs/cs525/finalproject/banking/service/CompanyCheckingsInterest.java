package edu.miu.cs.cs525.finalproject.banking.service;

import edu.miu.cs.cs525.finalproject.framework.service.InterestCalculation;

public class CompanyCheckingsInterest implements InterestCalculation {
    @Override
    public double calculateInterest(double balance) {
        if (balance < 1000) {
            return balance * 0.15;
        } else {
            return balance * 0.25;
        }
    }
}
