package com.example.bank_notification_system.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private static int accountCount = 0;
    private int accountId;
    private String userName;
    private int balance;
    private Channel channel;

    public Account(String userName, int balance, Channel channel) {
        this.accountId = accountCount++;
        this.userName = userName;
        this.balance = balance;
        this.channel = channel;
    }

    public static AccountBuilder builder() {
        return new AccountBuilder();
    }

    public static class AccountBuilder {
        private String userName;
        private int balance;
        private Channel channel;

        AccountBuilder() {
        }

        public AccountBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public AccountBuilder balance(int balance) {
            this.balance = balance;
            return this;
        }

        public AccountBuilder channel(Channel channel) {
            this.channel = channel;
            return this;
        }

        public Account build() {
            return new Account(this.userName, this.balance, this.channel);
        }
    }
}
