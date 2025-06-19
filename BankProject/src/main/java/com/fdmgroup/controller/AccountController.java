package com.fdmgroup.controller;

import com.fdmgroup.Bank.*;

import java.util.ArrayList;
import java.util.List;

public class AccountController {
    private List<Customer> customers = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Customer createCustomer(String name, String address, String type) {
        Customer customer = null;

        if ("p".equalsIgnoreCase(type)) {
            customer = new Person(name, address);
        } else if ("c".equalsIgnoreCase(type)) {
            customer = new Company(name, address);
        }

        if (customer != null) {
            customers.add(customer);
        }

        return customer;
    }

    public void removeCustomer(Customer customer) {
        if (customer == null) return;

        accounts.removeAll(customer.getAccounts());

        customers.remove(customer);
    }

    public Account createAccount(Customer customer, String type) {
        Account account = null;
        if ("c".equalsIgnoreCase(type)) {
            account = new CheckingAccount();
        } else if ("s".equalsIgnoreCase(type)) {
            account = new SavingsAccount();
        }

        if (account != null && customer != null) {
            accounts.add(account);
            customer.addAccount(account);
        }

        return account;
    }

    public void removeAccount(Account account) {
        accounts.remove(account);

        for (Customer customer : customers) {
            customer.removeAccount(account);
        }
    }
    
}
