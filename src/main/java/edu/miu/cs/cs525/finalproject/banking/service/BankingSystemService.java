package edu.miu.cs.cs525.finalproject.banking.service;

import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.AccountEntry;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;
import edu.miu.cs.cs525.finalproject.framework.service.AccountService;

public class BankingSystemService extends AccountService {
    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        for (Account account : getAllAccounts()) {
            Customer customer = account.getCustomer();
            report.append("Statement for Account: " + account.getAccountNumber() + "\n");
            report.append("Account Holder: " + customer.getName() + "\n");
            report.append("-Date--------------------------Description-------------------Amount-------------" + "\n");
            for (AccountEntry entry : account.getEntryList()) {
                String str = String.format("%30s%30s%20.2f\n",
                        entry.getDate().toString(),
                        entry.getDescription(),
                        entry.getAmount());
                report.append(str + "\n");
            }
            report.append("----------------------------------------" + "----------------------------------------" + "\n");
            String str = String.format("%30s%30s%20.2f\n\n", "", " Balance + interest:", account.getBalance());
            report.append(str + "\n");
        }
        return report.toString();
    }
}
