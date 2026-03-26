package com.example.boilerroom_labb1.mapper;

import com.example.boilerroom_labb1.dto.AuthorRequestDto;
import com.example.boilerroom_labb1.dto.AuthorResponseDto;
import com.example.boilerroom_labb1.dto.BookResponseDto;
import com.example.boilerroom_labb1.entity.Author;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorMapper {


    private final BookMapper bookMapper;

    public AuthorMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public AuthorResponseDto toResponseDto(Author author){
        List<BookResponseDto> books = author.getBooks().stream()
                .map(bookMapper::toResponseDto)
                .toList();

        return new AuthorResponseDto(
                author.getId(),
                author.getName(),
                books
        );
    }



    public Author toEntity(AuthorRequestDto request){
        return new Author(request.name());
    }
}
