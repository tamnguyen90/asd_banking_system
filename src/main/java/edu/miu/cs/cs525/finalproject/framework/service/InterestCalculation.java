package edu.miu.cs.cs525.finalproject.framework.service;

public interface InterestCalculation {
    double calculateInterest(double balance);
    default double getMonthlyInterest() {
        return 0.0;
    }
}
