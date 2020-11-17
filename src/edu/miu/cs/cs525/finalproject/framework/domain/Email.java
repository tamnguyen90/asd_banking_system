package edu.miu.cs.cs525.finalproject.framework.domain;

public class Email implements Observer {
    private Account subject;

    public Email(Account account) {
        this.subject = account;
        account.registerObserver(this);
    }
    @Override
    public void update(double amount) {
        //TODO send email to customer about changing
    }
}
