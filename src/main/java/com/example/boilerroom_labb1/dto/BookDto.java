package com.example.boilerroom_labb1.dto;

public class BookDto {
    private String title;
    private String author;
    private String isbn;
    private int publishedYear;


    public BookDto(String title, String author, String isbn, int publishedYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
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
