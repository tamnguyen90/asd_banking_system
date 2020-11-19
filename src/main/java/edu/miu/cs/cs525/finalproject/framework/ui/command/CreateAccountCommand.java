package edu.miu.cs.cs525.finalproject.framework.ui.command;

import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;
import edu.miu.cs.cs525.finalproject.framework.service.AccountService;

public class CreateAccountCommand extends Command {
    private Customer customer;
    private Account account;
    public CreateAccountCommand(Customer customer, Account account, AccountService accountService) {
        super(accountService);
        this.customer = customer;
        this.account = account;
    }

    @Override
    public boolean execute() {
        Account existAccount = accountService.getAccount(account.getAccountNumber());
        if (existAccount != null) {
            return false;
        }
        accountService.createAccount(customer, account);
        return true;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Account getAccount() {
        return account;
    }
}
