package com.example.boilerroom_labb1.mapper;


import com.example.boilerroom_labb1.dto.AuthorResponseDto;
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
                book.getAuthor().getName(),
                book.getIsbn(),
                book.getPublishedYear());

    }

    public Book toEntity(BookRequestDto request){
          Book book = new Book();
                book.setTitle(request.title());
                book.setIsbn(request.isbn());
                book.setPublishedYear(request.publishedYear());
                return book;
    }

    public BookResponseDtoV2 toResponseDtoV2(Book book){
        return new BookResponseDtoV2(
                book.getId(),
                book.getTitle(),
                new AuthorResponseDto(book.getAuthor().getId(),
                        book.getAuthor().getName()),
                book.getIsbn(),
                book.getPublishedYear(),
                true);
    }
}
