package com.example.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Message;

// JPA repository interface for Message
public interface MessageRepository extends JpaRepository<Message, Integer> {

    // Custom queries by method name and return type
    Message findById(int id);
    List<Message> findByPostedBy(int id);
}
