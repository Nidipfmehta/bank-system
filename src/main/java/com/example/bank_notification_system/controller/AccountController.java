package com.example.bank_notification_system.controller;

import com.example.bank_notification_system.dto.request.AccountDetailsRequest;
import com.example.bank_notification_system.dto.request.CreateAccountRequest;
import com.example.bank_notification_system.dto.request.TransactionRequest;
import com.example.bank_notification_system.exception.AccountNotFoundException;
import com.example.bank_notification_system.exception.InsufficientBalanceException;
import com.example.bank_notification_system.models.Account;
import com.example.bank_notification_system.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//localhost:8080/account

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    @GetMapping("/getAccount")
    public ResponseEntity<Account> getAccount(@RequestBody AccountDetailsRequest accountDetailsRequest) {
        try {
            Account account = accountService.getAccount(accountDetailsRequest);
            return ResponseEntity.ok(account);
        } catch (AccountNotFoundException e) {
            log.error("account not found ", e);
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            log.error("error ", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<String> getBalance(@RequestBody AccountDetailsRequest accountDetailsRequest) {
        try {
            return ResponseEntity.ok(String.format("%s your bank balance is %d. Sent via %s", accountService.getAccount(accountDetailsRequest).getUserName(),
                                                    accountService.getBalance(accountDetailsRequest), accountService.getAccount(accountDetailsRequest).getChannel()));
        }  catch (AccountNotFoundException e) {
            log.error("account not found ", e);
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            log.error("error ", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        Account account = accountService.createAccount(createAccountRequest);
        return ResponseEntity.ok(account);
    }

    @PatchMapping("/transaction")
    public ResponseEntity<String> performTransaction(@RequestBody TransactionRequest transactionRequest) {
        try {
            accountService.performTransaction(transactionRequest);
            return ResponseEntity.ok("transaction is performed");
        }  catch (AccountNotFoundException e) {
            log.error("account not found ", e);
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            log.error("error ", e);
            return ResponseEntity.status(500).body(null);
        }
    }

}
