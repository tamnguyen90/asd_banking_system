package edu.miu.cs.cs525.finalproject.creditcard.service;

import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.service.AccountService;

import java.util.Collection;

public class CreditCardSystemService extends AccountService {
    @Override
    public String generateReport() {
        Collection<Account> accounts = getAllAccounts();
        StringBuilder report = new StringBuilder();
        for (Account account : accounts) {
            report.append(account.generateReport() + "\n");
        }
        return report.toString();
    }
}
