package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Account;

// JPA repo for AccountRepo
public interface AccountRepository extends JpaRepository<Account, Integer> {

    // Custom queries by method name + return type
    Account findByUsername(String username);
    Account findByUsernameAndPassword(String username, String password);
    Account findById(int id);
}
