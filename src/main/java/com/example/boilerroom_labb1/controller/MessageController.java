package com.example.boilerroom_labb1.controller;

import com.example.boilerroom_labb1.entity.Message;
import com.example.boilerroom_labb1.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping("/hard")
    public Message create(){
        return service.create("hej yh", 42);
    }
    @PostMapping
    public Message create(@RequestBody Message message){
        return service.create(message);
    }

    @GetMapping
    public List<Message> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}/number")
    public int getNumber(@PathVariable Long id){ return service.getNumberById(id);}

    @GetMapping("/{id}/text")
    public String getText(@PathVariable Long id){ return service.getTextById(id);}
}
