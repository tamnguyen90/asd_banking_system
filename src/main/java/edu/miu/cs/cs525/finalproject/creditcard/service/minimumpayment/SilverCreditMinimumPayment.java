package edu.miu.cs.cs525.finalproject.creditcard.service.minimumpayment;

public class SilverCreditMinimumPayment implements MinimumPaymentCalculation {
    public double calculateMinimumPayment(double payment) {
return 0.12*payment;
    }

    @Override
    public double getMinimumPayment() {
        return 0.12;
    }

}
