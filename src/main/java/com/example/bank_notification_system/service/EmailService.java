package com.example.bank_notification_system.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@Slf4j
public class EmailService implements INotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendNotification(String msg, String to, String subject) throws MessagingException {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(msg);
            mailSender.send(message);
            log.info("EMAIL sent to {}", to);
        } catch (MailException e) {
            log.error("Email sending failed to {}", to, e);
            throw new MessagingException("Email sending failed", e);
        }
    }
}

//@Slf4j
//public class EmailService implements INotificationService {
//
//    private final String fromEmail = "nfmehta1408@gmail.com";      // Sender Email
//    private final String emailPassword = "blackpearl1408";       // Sender Email Password
//
//    @Override
//    public void sendNotification(String toEmail, String subject, String body) {
//        // SMTP Server Properties
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");  // SMTP Host
//        props.put("mail.smtp.port", "587");              // TLS Port
//        props.put("mail.smtp.auth", "true");             // Enable authentication
//        props.put("mail.smtp.starttls.enable", "true");  // Enable STARTTLS
//
//        // Create a Session with authentication
//        Session session = Session.getInstance(props, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(fromEmail, emailPassword);
//            }
//        });
//
//        try {
//            // Create the Email Message
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(fromEmail));
//            message.setRecipients(
//                    Message.RecipientType.TO,
//                    InternetAddress.parse(toEmail)
//            );
//            message.setSubject(subject);
//            message.setText(body);
//
//            // Send the Email
//            Transport.send(message);
//
//            log.info("Email successfully sent to {}", toEmail);
//
//        } catch (MessagingException e) {
//            log.error("Failed to send email to {}", toEmail, e);
//        }
//    }
//}
