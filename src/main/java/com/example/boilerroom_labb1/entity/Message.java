package com.example.boilerroom_labb1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private int number;

    public Message() {
    }

    public Message(String text, int number){
        this.text = text;
        this.number = number;
    }

    public Long getId(){ return id; }

    public String getText() {
        return text;
    }

    public int getNumber() {
        return number;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

