package com.example.bank_notification_system.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailService implements INotificationService {

    @Override
    public void sendNotification() {
        log.info("notification sent through EMAIL");
    }
}