package com.example.boilerroom_labb1.service;

import com.example.boilerroom_labb1.entity.Message;
import com.example.boilerroom_labb1.repository.MessageRepository;
import org.springframework.boot.data.metrics.DefaultRepositoryTagsProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class MessageService {
    private final MessageRepository repository;


    public MessageService(MessageRepository repository) {
        this.repository = repository;

    }
    public Message create(String text, int number){
        Message message = new Message(text, number);
        return repository.save(message);
    }

    public Message create(Message message){
        return repository.save(message);
    }

    public List<Message> getAll(){
        return repository.findAll();
    }

  public int getNumberById(Long id){
        Message message = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        return message.getNumber();

    }

    public String getTextById(Long id){
        Message message = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        return message.getText();
    }
}
