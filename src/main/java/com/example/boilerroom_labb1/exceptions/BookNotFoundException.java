package com.example.boilerroom_labb1.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Book not found with id: " + id);
    }
}
