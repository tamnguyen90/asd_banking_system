package edu.miu.cs.cs525.finalproject.creditcard.domain;

import edu.miu.cs.cs525.finalproject.creditcard.service.minimumpayment.MinimumPaymentCalculation;
import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.AccountEntry;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public abstract class CreditCardAccount extends Account {
    private LocalDate expiredDate;
    protected MinimumPaymentCalculation minimumPaymentCalculation;

    public CreditCardAccount(String accountNumber, String type, Customer customer, LocalDate expiredDate) {
        super(accountNumber, type, customer);
        this.expiredDate = expiredDate;
    }

    public void addPayment() {
        AccountEntry entry = new AccountEntry(-minimumPaymentCalculation.calculateMinimumPayment(this.getBalance()), "interest adding");
        getEntryList().add(entry);
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public void withdrawNotification(double amount) {
        if (getBalance() < 0 || amount > 400) {
            String message = "Your account has just been withdrawn $" + amount + ". The current balance is $" + getBalance() + ".";
            notifyObservers(message);
        }
    }

    @Override
    public void depositNotification(double amount) {
        if (getBalance() < 0 || amount > 400) {
            String message = "Your account has just been deposited $" + amount + ". The current balance is $" + getBalance() + ".";
            notifyObservers(message);
        }
    }

    public double calculatePreviousBalance() {
        //previous balance: balance from last month
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfMonth(1);

        return getEntryList().stream()
                .filter(entry -> entry.getDate().isBefore(firstDay))
                .filter(entry -> entry.getDescription().equals("deposit") || entry.getDescription().equals("withdraw"))
                .mapToDouble(AccountEntry::getAmount).sum();
    }

    public double calculateTotalCharges() {
        //total charges: total of all charges for this month
        LocalDate today = LocalDate.now();
        LocalDate firstDate = today.withDayOfMonth(1);
        LocalDate lastDate = today.withDayOfMonth(today.lengthOfMonth());

        return this.getEntryList().stream()
                .filter(entry -> entry.getDate().isBefore(lastDate))
                .filter(entry -> entry.getDate().isAfter(firstDate))
                .filter(entry -> entry.getDescription().equals("withdraw"))
                .mapToDouble(AccountEntry::getAmount).sum();
    }

    public double calculateTotalCredits() {
        //total credits: total of all payments for this month
        LocalDate today = LocalDate.now();
        LocalDate firstDate = today.withDayOfMonth(1);
        LocalDate lastDate = today.withDayOfMonth(today.lengthOfMonth());
        return this.getEntryList().stream()
                .filter(entry -> entry.getDate().isBefore(lastDate))
                .filter(entry -> entry.getDate().isAfter(firstDate))
                .filter(entry -> entry.getDescription().equals("deposit"))
                .mapToDouble(AccountEntry::getAmount).sum();
    }

    public double calculateNewBalance() {
        //new balance = previous balance – total credits + total charges + MI * (previous balance – total credits)
        double previousBalances = calculatePreviousBalance();
        double totalCredits = calculateTotalCredits();
        double totalCharges = calculateTotalCharges();
        return previousBalances - totalCredits + totalCharges
                + interest.getMonthlyInterest() * (previousBalances - totalCredits);
    }

    public double calculateTotalDue() {
        //total due = MP * new balance
        return minimumPaymentCalculation.getMinimumPayment() * calculateNewBalance();
    }

    @Override
    public String generateReport() {

        double previousBalance = calculatePreviousBalance();
        double totalCharges = calculateTotalCharges();
        double totalCredits = calculateTotalCredits();
        double newBalance = calculateNewBalance();
        double totalDue = calculateTotalDue();

        StringBuilder report = new StringBuilder();
        report.append("Name: " + getCustomer().getName() + '\n');
        report.append(getCustomer().getAddress().toString() + '\n');
        report.append( "\rCC number: " + getAccountNumber() + '\n') ;
        report.append("\rCC type: "+ getType() + '\n');
        report.append( "\rPrevious balance: $" + previousBalance + '\n');
        report.append( "Total charge: $ " + totalCharges + '\n');
        report.append( "Total credit: $ " + totalCredits + '\n');
        report.append( "New Balance: $ " + newBalance + '\n');
        report.append( "Total Amount Due: $ " + (totalDue < 0 ? 0.0 : totalDue) + '\n');
        report.append( "\r");
        return report.toString();
    }
}
