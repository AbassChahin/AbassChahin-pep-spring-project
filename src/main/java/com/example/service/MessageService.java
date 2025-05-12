package com.example.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.MessageRepository;
import com.example.entity.Message;

// Service annotation
@Service
public class MessageService {
    // Service member variables
    MessageRepository messageRepository;
    AccountService accountService;

    // Dependency injection
    @Autowired
    public MessageService(MessageRepository messageRepository, AccountService accountService) {
        this.messageRepository = messageRepository;
        this.accountService = accountService;
    }

    // Add a message
    public Message addMessage(Message message) {
        if (message.getMessageText() != null && !message.getMessageText().isEmpty()) {
            if (message.getMessageText().length() < 255) {
                if (accountService.getAccountById(message.getPostedBy()) != null) {
                    return messageRepository.save(message);
                }
            }
        }
        return null;
    }

    // Get list of all messages
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // Get message by id
    public Message getMessageById(Integer id) {
        return messageRepository.findById((int) id);
    }

    // Delete message by its id
    public Integer deleteMessageById(Integer id) {
        if (getMessageById(id) != null) {
            messageRepository.deleteById(id);
            return 1;
        } else {
            return null;
        }
    }

    // Update message by its id and given message text
    public Integer updateMessageById(Integer id, String updatedMessageText) {
        Message message = getMessageById(id);
        if (message != null) {
            if (updatedMessageText != null && !updatedMessageText.isEmpty()) {
                if (updatedMessageText.length() <= 255) {
                    message.setMessageText(updatedMessageText);
                    messageRepository.save(message);
                    return 1;
                }
            }
        }
        return 0;
    }

    // Get all messages given account id
    public List<Message> getAllMessageById(Integer id) {
        return messageRepository.findByPostedBy((int) id);
    }
}
