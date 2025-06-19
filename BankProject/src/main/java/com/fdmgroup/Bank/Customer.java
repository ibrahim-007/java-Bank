package com.fdmgroup.Bank;

import java.util.ArrayList;
import java.util.List;

public abstract class Customer {
    private static int idCounter = 100;
    private final int CUSTOMER_ID;
    private String name;
    private String address;
    private List<Account> accounts = new ArrayList<>();

    public Customer(String name, String address) {
        this.CUSTOMER_ID = idCounter++;
        this.name = name;
        this.address = address;
    }

    public int getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public String getName() {
        return name;
    }
    
    public String getAddress() {
    	return address;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public abstract void chargeAllAccounts(double amount);
}
