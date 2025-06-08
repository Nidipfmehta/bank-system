package com.example.bank_notification_system.service;

import com.example.bank_notification_system.exception.MessagingException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import com.twilio.type.PhoneNumber;


@Service
@Slf4j
public class SmsService implements INotificationService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    @Override
    public void sendNotification(String msg, String to, String subject) {
        try {
            Message message = Message.creator(
                    new PhoneNumber(to),        // To number
                    new PhoneNumber(fromPhoneNumber), // From Twilio number
                    msg                         // SMS body
            ).create();

            log.info("SMS sent to {} with SID: {}", to, message.getSid());
        } catch (Exception e) {
            log.error("SMS sending failed to {}", to, e);
            throw new RuntimeException("SMS sending failed", e);
        }
    }
}
