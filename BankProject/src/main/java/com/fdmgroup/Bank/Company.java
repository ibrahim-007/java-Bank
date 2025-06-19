package com.fdmgroup.Bank;

public class Company extends Customer {
    public Company(String name, String address) {
        super(name, address);
    }

    @Override
    public void chargeAllAccounts(double amount) {
        for (Account account : getAccounts()) {
            account.charge(amount); // Let the account decide what to do
        }
    }
}
