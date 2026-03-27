package com.example.boilerroom_labb1.service;

import com.example.boilerroom_labb1.dto.AuthorRequestDto;
import com.example.boilerroom_labb1.dto.AuthorResponseDto;
import com.example.boilerroom_labb1.dto.BookRequestDto;
import com.example.boilerroom_labb1.dto.BookResponseDto;
import com.example.boilerroom_labb1.entity.Author;
import com.example.boilerroom_labb1.exceptions.NotFoundWithIdException;
import com.example.boilerroom_labb1.exceptions.ResourceNotFoundException;
import com.example.boilerroom_labb1.mapper.AuthorMapper;
import com.example.boilerroom_labb1.mapper.BookMapper;
import com.example.boilerroom_labb1.repository.AuthorRepository;
import com.example.boilerroom_labb1.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthorService {



    private final AuthorRepository repository;
    private final AuthorMapper mapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    public AuthorService (AuthorRepository repository, AuthorMapper mapper, BookRepository bookRepository, BookMapper bookMapper){
        this.repository = repository;
        this.mapper = mapper;
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }


    public AuthorResponseDto createEntity(AuthorRequestDto request){
        Author author = mapper.toEntity(request);
        repository.save(author);
         return mapper.toResponseDto(author);
    }

    public AuthorResponseDto getAuthorById(Long id){
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new NotFoundWithIdException("Author not found with id: ", + id));
    }
    public List<BookResponseDto> getBooksByAuthor(Long authorId) {

        if (!repository.existsById(authorId)) {
            throw new ResourceNotFoundException("Author not found");
        }

        return bookRepository.findByAuthorId(authorId)
                .stream()
                .map(bookMapper::toResponseDto)
                .toList();
    }
}
