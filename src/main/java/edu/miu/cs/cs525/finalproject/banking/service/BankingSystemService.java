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
            report.append(account.generateReport() + "\n");
        }
        return report.toString();
    }
}
