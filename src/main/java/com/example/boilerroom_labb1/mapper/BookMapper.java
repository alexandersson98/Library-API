package com.example.boilerroom_labb1.mapper;


import com.example.boilerroom_labb1.dto.BookRequestDto;
import com.example.boilerroom_labb1.dto.BookResponseDto;
import com.example.boilerroom_labb1.dto.BookResponseDtoV2;
import com.example.boilerroom_labb1.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookResponseDto toResponseDto(Book book){
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublishedYear());

    }

    public Book toEntity(BookRequestDto request){
        return new Book(request.title(),
                request.author(),
                request.isbn(),
                request.publishedYear());
    }

    public BookResponseDtoV2 toResponseDtoV2(Book book){
        return new BookResponseDtoV2(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublishedYear(),
                true);
    }
}
