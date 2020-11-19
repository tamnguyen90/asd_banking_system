package edu.miu.cs.cs525.finalproject.banking.service;

import edu.miu.cs.cs525.finalproject.framework.domain.InterestCalculation;

public class CompanySavingsInterest implements InterestCalculation {
    @Override
    public double calculateInterest() {
        System.out.println("Calculating interest for Company savings account.");
        return 0;
    }
}
