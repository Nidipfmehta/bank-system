package com.example.bank_notification_system.service;

import com.example.bank_notification_system.exception.MessagingException;

public interface INotificationService {
//    void sendNotification(String msg) throws MessagingException;
    void sendNotification(String msg, String to, String subject) throws MessagingException, javax.mail.MessagingException;
}
