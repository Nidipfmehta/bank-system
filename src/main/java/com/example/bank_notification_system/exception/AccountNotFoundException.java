package com.example.bank_notification_system.exception;

public class AccountNotFoundException extends Exception{
    public AccountNotFoundException(String e) {
        super(e);
    }

    public AccountNotFoundException(String e, Throwable cause) {
        super(e, cause);
    }
}
