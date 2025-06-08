package com.example.bank_notification_system.models;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private static int accountCount = 0;
    private int accountId;
    private String userName;
    private int balance;
    private Channel channel;

    // Add this to auto-generate accountId
    public Account(String userName, int balance, Channel channel) {
        this.accountId = accountCount++;
        this.userName = userName;
        this.balance = balance;
        this.channel = channel;
    }

    public String getUserName() {
        return userName;
    }

    public Channel getChannel() {
        return channel;
    }

    public int getBalance() {
        return balance;
    }


}
