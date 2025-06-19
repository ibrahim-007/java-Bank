package com.fdmgroup.Bank;

public class SavingsAccount extends Account {
    public SavingsAccount() {
        super();
    }

    @Override
    public void charge(double amount) {
        withdraw(amount * 2);
    }
}
