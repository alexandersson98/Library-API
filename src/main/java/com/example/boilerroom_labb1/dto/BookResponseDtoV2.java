package com.example.boilerroom_labb1.dto;

public record BookResponseDtoV2(Long id, String title, String author, String isbn, int publishedYear, boolean available) {
}
