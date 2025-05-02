package com.example.bank_notification_system.exception;

public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(String e) {
        super(e);
    }

    public InsufficientBalanceException(String e, Throwable cause) {
        super(e, cause);
    }
}
