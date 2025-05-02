package com.example.bank_notification_system.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsService implements INotificationService {
    @Override
    public void sendNotification() {
        log.info("notification sent through SMS");
    }
}
