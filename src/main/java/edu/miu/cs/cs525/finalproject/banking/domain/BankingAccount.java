package edu.miu.cs.cs525.finalproject.banking.domain;

import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.AccountEntry;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;

public abstract class BankingAccount extends Account {

    public BankingAccount(String accountNumber, String type, Customer customer) {
        super(accountNumber, type, customer);
    }

    @Override
    public void depositNotification(double amount) {
        String message = "Your account has just been deposited $" + amount + ". The current balance is $" + getBalance() + ".";
        notifyObservers(message);
    }

    @Override
    public void withdrawNotification(double amount) {
        String message = "Your account has just been withdrawn $" + amount + ". The current balance is $" + getBalance() + ".";
        notifyObservers(message);
    }

    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        Customer customer = getCustomer();
        report.append("Statement for Account: " + getAccountNumber() + "\n");
        report.append("Account Holder: " + customer.getName() + "\n");
        report.append("--------------Date---------"
                + "--Description-----"
                + "--Amount-------------" + "\n");
        for (AccountEntry entry : getEntryList()) {
            String str = String.format("%30s%30s%20.2f\n",
                    entry.getDate().toString(),
                    entry.getDescription(),
                    entry.getAmount());
            report.append(str + "\n");
        }
        report.append("----------------------------------------" + "---------------------------" + "\n");
        String str = String.format("%30s%30s%20.2f\n\n", "", " Balance + interest:", getBalance());
        report.append(str + "\n");
        return report.toString();
    }
}
