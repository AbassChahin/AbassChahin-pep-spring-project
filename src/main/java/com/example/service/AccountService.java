package com.example.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    // Account Repositroy member var
    AccountRepository accountRepository;

    // Dependency injection
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Add account to table
    public Account addAccount(Account account) {
        if (account.getUsername() != null && !account.getUsername().isEmpty()) {
            if (account.getPassword().length() >= 4) {
                if (getAccountByUsername(account.getUsername()) == null) {
                    return accountRepository.save(account);
                }
            }
        }
        return null;
    }

    // Get all classrooms
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Get account by its username
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    // Get account by username and password
    public Account getAccountByUsernameAndPassword(String username, String password) {
        return accountRepository.findByUsernameAndPassword(username, password);
    }

    // Get account by id
    public Account getAccountById(Integer id) {
        return accountRepository.findById((int) id);
    }

}
