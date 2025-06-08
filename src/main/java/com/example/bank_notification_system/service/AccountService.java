package com.example.bank_notification_system.service;

import com.example.bank_notification_system.dto.request.AccountDetailsRequest;
import com.example.bank_notification_system.dto.request.CreateAccountRequest;
import com.example.bank_notification_system.dto.request.TransactionRequest;
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

    public Account getAccount(final AccountDetailsRequest accountDetailsRequest) throws AccountNotFoundException {
        return accountRepository.getAccount(accountDetailsRequest);
    }

    public int getBalance(final AccountDetailsRequest accountDetailsRequest) throws AccountNotFoundException {
        return getAccount(accountDetailsRequest).getBalance();
    }

    public Account createAccount(final CreateAccountRequest createAccountRequest) {
        INotificationService notificationService = notificationServiceFactory.getNotificationService(createAccountRequest.getChannel());
//        notificationService.sendNotification();
        return accountRepository.createAccount(createAccountRequest);
    }

    public void performTransaction(final TransactionRequest transactionRequest) throws InsufficientBalanceException, AccountNotFoundException {
//        accountRepository.performTransaction(accountId1, accountId2, money);
        Account sender = getAccount(transactionRequest.getSender());
       Account receiver = getAccount(transactionRequest.getReceiver());
       if(sender.getBalance() >= transactionRequest.getAmount()) {
           sender.setBalance(sender.getBalance() - transactionRequest.getAmount());
           receiver.setBalance(receiver.getBalance() + transactionRequest.getAmount());
        }
        throw new InsufficientBalanceException(String.format("Insufficient balance of account %d to perform transaction", sender.getAccountId()));
    }
}
