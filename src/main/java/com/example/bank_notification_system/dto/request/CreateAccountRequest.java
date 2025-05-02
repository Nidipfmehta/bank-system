package com.example.bank_notification_system.dto.request;

import com.example.bank_notification_system.models.Channel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class CreateAccountRequest {
    private String userName;
    private int balance;
    private Channel channel;
}
