package com.example.bank_notification_system.repository;

import com.example.bank_notification_system.dto.request.AccountDetailsRequest;
import com.example.bank_notification_system.dto.request.CreateAccountRequest;
import com.example.bank_notification_system.exception.AccountNotFoundException;
import com.example.bank_notification_system.exception.InsufficientBalanceException;
import com.example.bank_notification_system.models.Account;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class InMemoryAccountRepository implements IAccountRepository {
    private final List<Account> accountList = new ArrayList<>();


    @Override
    public Account getAccount(AccountDetailsRequest accountDetailsRequest) throws AccountNotFoundException {
        for(Account account : accountList) {
            if(account.getAccountId() == accountDetailsRequest.getAccountId()) {
                return account;
            }
        }
        throw new AccountNotFoundException(String.format("account id %d not found", accountDetailsRequest.getAccountId()));
    }

    @Override
    public Account createAccount(CreateAccountRequest createAccountRequest) {
        Account account = new Account(createAccountRequest.getUserName(), createAccountRequest.getBalance(), createAccountRequest.getChannel());
        accountList.add(account);
        return account;
    }

}
