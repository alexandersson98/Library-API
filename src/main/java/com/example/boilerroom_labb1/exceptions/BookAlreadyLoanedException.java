package com.example.boilerroom_labb1.exceptions;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(String message) {
    super(message);
  }
}
