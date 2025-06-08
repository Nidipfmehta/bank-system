package com.example.bank_notification_system.repository;

import com.example.bank_notification_system.dto.request.AccountDetailsRequest;
import com.example.bank_notification_system.dto.request.CreateAccountRequest;
import com.example.bank_notification_system.exception.AccountNotFoundException;
import com.example.bank_notification_system.exception.InsufficientBalanceException;
import com.example.bank_notification_system.models.Account;


public interface IAccountRepository {
    Account getAccount(AccountDetailsRequest accountDetailsRequest) throws AccountNotFoundException;
    Account createAccount(CreateAccountRequest createAccountRequest);
}
