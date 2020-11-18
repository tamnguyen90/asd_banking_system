package edu.miu.cs.cs525.finalproject.banking;

import edu.miu.cs.cs525.finalproject.framework.domain.InterestCalculation;

public class CompanyCheckingsInterest implements InterestCalculation {
    @Override
    public double calculateInterest() {
        System.out.println("Calculating company checkings account interest");
        return 0;
    }
}
