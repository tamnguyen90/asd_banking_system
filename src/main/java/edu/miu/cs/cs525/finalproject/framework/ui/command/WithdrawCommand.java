package edu.miu.cs.cs525.finalproject.framework.ui.command;

import edu.miu.cs.cs525.finalproject.framework.service.AccountService;

public class WithdrawCommand extends Command {
    private String accountNumber;
    private double amount;
    public WithdrawCommand(AccountService accountService, String accountNumber, double amount) {
        super(accountService);
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    @Override
    public boolean execute() {
        accountService.withdraw(accountNumber, amount);
        return true;
    }
}
