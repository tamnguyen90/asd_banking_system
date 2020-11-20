package edu.miu.cs.cs525.finalproject.creditcard.service.interest;

import edu.miu.cs.cs525.finalproject.framework.service.InterestCalculation;

public class SilverCreditCardAccountInterest implements InterestCalculation {
    @Override
    public double calculateInterest(double balance) {
        return balance*0.8;
    }

    @Override
    public double getMonthlyInterest() {
        return 0.8;
    }
}
