package com.example.bank_notification_system.repository;

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
    public Account getAccount(int accountId) throws AccountNotFoundException {
        for(Account account : accountList) {
            if(account.getAccountId() == accountId) {
                return account;
            }
        }
        throw new AccountNotFoundException(String.format("account id %d not found", accountId));
    }

    @Override
    public Account createAccount(CreateAccountRequest createAccountRequest) {
        Account account = new Account(createAccountRequest.getUserName(), createAccountRequest.getBalance(), createAccountRequest.getChannel());
        accountList.add(account);
        return account;
    }

//    @Override
//    public void performTransaction(int accountId1, int accountId2, int money) throws AccountNotFoundException, InsufficientBalanceException {
//        Account account1 = getAccount(accountId1);
//        Account account2 = getAccount(accountId2);
//        if(account1.getBalance() >= money) {
//            account1.setBalance(account1.getBalance() - money);
//            account2.setBalance(account2.getBalance() + money);
//        }
//        throw new InsufficientBalanceException(String.format("Insufficient balance of account %d to perform transaction", accountId1));
//    }
}
