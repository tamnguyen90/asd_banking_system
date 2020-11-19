package edu.miu.cs.cs525.finalproject.framework.ui.command;

import edu.miu.cs.cs525.finalproject.framework.service.AccountService;

public class AddInterestCommand extends Command {

    public AddInterestCommand(AccountService accountService) {
        super(accountService);
    }

    @Override
    public boolean execute() {
        accountService.addInterests();
        return true;
    }
}
