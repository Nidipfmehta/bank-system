package com.example.bank_notification_system.dto.request;

import com.example.bank_notification_system.models.Channel;
import lombok.Getter;

@Getter
public class TransactionRequest {
    private AccountDetailsRequest sender;
    private AccountDetailsRequest receiver;
    private int amount;
}
