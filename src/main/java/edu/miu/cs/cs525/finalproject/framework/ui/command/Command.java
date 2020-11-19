package edu.miu.cs.cs525.finalproject.framework.ui.command;

import edu.miu.cs.cs525.finalproject.framework.service.AccountService;

public abstract class Command {
    protected AccountService accountService;

    public Command(AccountService accountService) {
        this.accountService = accountService;
    }
    public abstract boolean execute();
}
