package com.example.controller;

import java.util.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.entity.Account;
import com.example.entity.Message;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 // RestController annotation
@RestController
public class SocialMediaController {
    // Service Member Vars
    AccountService accountService;
    MessageService messageService;

    // Dependency injection
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }


    // Create New Account
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Account newAccount) {
        try {
            if (accountService.addAccount(newAccount) != null) {
                return ResponseEntity.status(200)
                    .body("");
                
            } else {
                return ResponseEntity.status(409)
                    .body("");
            }
        } catch (Exception e) {
            return ResponseEntity.status(400)
                    .body("");
        }
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        Account lookForAccount = accountService.getAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (lookForAccount != null) {
            return ResponseEntity.status(200)
                .body(lookForAccount);
        } else {
            return ResponseEntity.status(401)
                .body(null);
        }
    }

    // Create New Message
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message newMessage = messageService.addMessage(message);
        if (newMessage != null) {
            return ResponseEntity.status(200)
                .body(newMessage);
        } else {
            return ResponseEntity.status(400)
                .body(null);
        }
    }

    // Get All Messages
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.status(200)
            .body(messageService.getAllMessages());
    }

    // Get One Message given ID
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer message_id) {
        return ResponseEntity.status(200)
            .body(messageService.getMessageById(message_id));
    }
    
    // Delete message given ID
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable Integer message_id) {
        return ResponseEntity.status(200)
            .body(messageService.deleteMessageById(message_id));
    }

    // Update message given id
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessageById(@PathVariable Integer message_id, @RequestBody Map<String, String> message_text) {
        String messageText = message_text.get("messageText");
        int result = messageService.updateMessageById(message_id, messageText);
        if (result == 1) {
            return ResponseEntity.status(200)
                .body(1);
        } else {
            return ResponseEntity.status(400)
                .body(null);
        }
    }

    // Get all messages by account id
    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesById(@PathVariable Integer account_id) {
        return ResponseEntity.status(200)
            .body(messageService.getAllMessageById(account_id));
    }
}
