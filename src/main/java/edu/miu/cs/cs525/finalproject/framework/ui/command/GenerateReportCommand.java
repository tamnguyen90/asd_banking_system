package edu.miu.cs.cs525.finalproject.framework.ui.command;

import edu.miu.cs.cs525.finalproject.framework.service.AccountService;

public class GenerateReportCommand extends Command {
    public GenerateReportCommand(AccountService accountService) {
        super(accountService);
    }

    @Override
    public boolean execute() {
        String report = accountService.generateReport();
        return true;
    }
}
