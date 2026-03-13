package com.example.boilerroom_labb1.dto;

public class CreateBookRequest {
    private String title;
    private String author;
    private String isbn;
    private int publishedYear;


    public CreateBookRequest() {
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPublishedYear() {
        return publishedYear;
    }
}
