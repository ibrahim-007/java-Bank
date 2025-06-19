package com.fdmgroup.Bank;

public abstract class Account {
    private static int idCounter = 1000;
    private final int ACCOUNT_ID;
    private double balance;

    public Account() {
        this(0);
        }

    public Account(double balance) {
        this.ACCOUNT_ID = idCounter;
        idCounter += 5;
        this.balance = balance;
    }

    public int getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public void correctBalance(double newBalance) {
        this.balance = newBalance;
    }

    public abstract void charge(double amount);
}
