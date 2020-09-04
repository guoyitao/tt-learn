package com.guo.javaconcurrencyinpractice.tenDieLock;

import java.math.BigInteger;

public class Account {
    private Long balance;

    public Account(Long balance) {
        this.balance = balance;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

//    public debit()
}
