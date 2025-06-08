package com.example.bank_notification_system.dto.request;

import com.example.bank_notification_system.models.Channel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class AccountDetailsRequest {
    private int accountId;
    private String userName;
    private int balance;
    private Channel channel;
}
