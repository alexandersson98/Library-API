package com.example.boilerroom_labb1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.boilerroom_labb1.entity.Message;

public interface MessageRepository extends  JpaRepository<Message, Long> {}

