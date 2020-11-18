package edu.miu.cs.cs525.finalproject.framework.dao;

import edu.miu.cs.cs525.finalproject.framework.domain.Account;

import java.util.Collection;

public interface AccountDAO {
    void saveAccount(Account account);
    void updateAccount(Account account);
    Account loadAccount(String accountNumber);
    Collection<Account> getAccounts();
    
    //Hello Team, This line is the code I changed for testing purpose
}
