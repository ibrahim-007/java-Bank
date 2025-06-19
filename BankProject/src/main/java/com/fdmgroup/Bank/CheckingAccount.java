package com.fdmgroup.Bank;

public class CheckingAccount extends Account {
    public CheckingAccount() {
        super();
    }

    @Override
    public void charge(double amount) {
        withdraw(amount);
    }
}
