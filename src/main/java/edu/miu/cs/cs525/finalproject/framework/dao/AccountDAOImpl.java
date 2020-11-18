package edu.miu.cs.cs525.finalproject.framework.dao;

import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;

import java.util.*;
import java.util.stream.Collectors;

public class AccountDAOImpl implements AccountDAO {
    Map<Customer, Collection<Account>> accountMap = new HashMap<>();

    public void saveAccount(Customer customer, Account account) {
        Collection<Account> accounts = new ArrayList<>();
        Collection<Customer> customerIds = accountMap.keySet();
        if (!customerIds.contains(customer)) {
            accounts.add(account);
            accountMap.put(customer, accounts);
        } else {
            accounts = accountMap.get(customer);
            accounts.add(account);
        }
    }

    public void updateAccount(String customerId, Account account) {
        Customer customer = getCustomerById(customerId);
        Collection<Account> accounts = accountMap.get(customer);
        Account currentAccount = loadAccount(account.getAccountNumber());
        if (currentAccount != null) {
            accounts.remove(currentAccount); // remove the old
            accounts.add(account); // add the new
        }

    }

    public Account loadAccount(String accountNumber) {
        return accountMap.values().stream()
                                    .flatMap(x -> x.stream())
                                    .filter(acct -> acct.getAccountNumber().equals(accountNumber))
                                    .findFirst()
                                    .orElse(null);
    }

    public Collection<Account> getAccountsByCustomer(String customerId) {
        Customer customer = getCustomerById(customerId);
        return accountMap.get(customer);
    }

    public Collection<Account> getAccounts() {
        return accountMap.values().stream()
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
    }

    public Customer getCustomerById(String customerId) {
        return accountMap.keySet().stream()
                .filter(cust -> customerId.equals(cust.getCustomerId()))
                .findFirst()
                .orElse(null);
    }
}
