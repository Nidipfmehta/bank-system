package com.example.bank_notification_system.factory;

import com.example.bank_notification_system.exception.ChannelNotFoundException;
import com.example.bank_notification_system.models.Channel;
import com.example.bank_notification_system.service.EmailService;
import com.example.bank_notification_system.service.INotificationService;
import com.example.bank_notification_system.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceFactory {
    private final SmsService smsService;
    private final EmailService emailService;

    public INotificationService getNotificationService(Channel channel) {
        return switch (channel) {
            case SMS -> smsService;
            case EMAIL -> emailService;
            default -> throw new ChannelNotFoundException(String.format("channel not found"));
        };
    }
}
