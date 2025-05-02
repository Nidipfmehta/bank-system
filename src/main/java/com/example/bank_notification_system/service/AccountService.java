package com.example.bank_notification_system.service;

import com.example.bank_notification_system.dto.request.CreateAccountRequest;
import com.example.bank_notification_system.exception.AccountNotFoundException;
import com.example.bank_notification_system.exception.InsufficientBalanceException;
import com.example.bank_notification_system.factory.NotificationServiceFactory;
import com.example.bank_notification_system.models.Account;
import com.example.bank_notification_system.repository.IAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final IAccountRepository accountRepository;
//    private INotificationService notificationService;
    private final NotificationServiceFactory notificationServiceFactory;

    public Account getAccount(final int accountId) throws AccountNotFoundException {
        return accountRepository.getAccount(accountId);
    }

    public int getBalance(final int accountId) throws AccountNotFoundException {
        return getAccount(accountId).getBalance();
    }

    public Account createAccount(final CreateAccountRequest createAccountRequest) {
        INotificationService notificationService = notificationServiceFactory.getNotificationService(createAccountRequest.getChannel());
        notificationService.sendNotification();
        return accountRepository.createAccount(createAccountRequest);
    }

    public void performTransaction(final int accountId1, final int accountId2, final int money) throws InsufficientBalanceException, AccountNotFoundException {
//        accountRepository.performTransaction(accountId1, accountId2, money);
        Account account1 = getAccount(accountId1);
       Account account2 = getAccount(accountId2);
       if(account1.getBalance() >= money) {
            account1.setBalance(account1.getBalance() - money);
            account2.setBalance(account2.getBalance() + money);
        }
        throw new InsufficientBalanceException(String.format("Insufficient balance of account %d to perform transaction", accountId1));
    }
}
