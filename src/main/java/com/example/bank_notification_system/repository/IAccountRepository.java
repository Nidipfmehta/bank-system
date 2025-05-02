package com.example.bank_notification_system.repository;

import com.example.bank_notification_system.dto.request.CreateAccountRequest;
import com.example.bank_notification_system.exception.AccountNotFoundException;
import com.example.bank_notification_system.exception.InsufficientBalanceException;
import com.example.bank_notification_system.models.Account;

public interface IAccountRepository {
    Account getAccount(int accountId) throws AccountNotFoundException;
    Account createAccount(CreateAccountRequest createAccountRequest);
//    void performTransaction(int accountId1, int accountId2, int money) throws AccountNotFoundException, InsufficientBalanceException;
}
