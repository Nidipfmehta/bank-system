package com.example.bank_notification_system.controller;

import com.example.bank_notification_system.dto.request.CreateAccountRequest;
import com.example.bank_notification_system.exception.AccountNotFoundException;
import com.example.bank_notification_system.models.Account;
import com.example.bank_notification_system.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//localhost:8080/account

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private AccountService accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable("accountId") int accountId) {
        try {
            Account account = accountService.getAccount(accountId);
            return ResponseEntity.ok(account);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<String> getBalance(@PathVariable("accountId") int accountId) {
        try {
            return ResponseEntity.ok(String.format("%s your bank balance is %d. Sent via %s", accountService.getAccount(accountId).getUserName(),
                                                    accountService.getBalance(accountId), accountService.getAccount(accountId).getChannel()));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        Account account = accountService.createAccount(createAccountRequest);
        return ResponseEntity.ok(account);
    }

    @PatchMapping("/{accountId1}/{accountId2}/{money}")
    public ResponseEntity<String> performTransaction(@PathVariable("accountId1") int accountId1,
                                                     @PathVariable("accountId2") int accountId2,
                                                     @PathVariable("money") int money) {
        try {
            accountService.performTransaction(accountId1, accountId2, money);
            return ResponseEntity.ok("transaction is performed");
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
