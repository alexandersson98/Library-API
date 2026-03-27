package com.example.boilerroom_labb1.service;

import com.example.boilerroom_labb1.dto.AuthorRequestDto;
import com.example.boilerroom_labb1.dto.AuthorResponseDto;
import com.example.boilerroom_labb1.dto.BookResponseDto;
import com.example.boilerroom_labb1.entity.Author;
import com.example.boilerroom_labb1.exceptions.NotFoundWithIdException;
import com.example.boilerroom_labb1.mapper.AuthorMapper;
import com.example.boilerroom_labb1.repository.AuthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AuthorService {



    private final AuthorRepository repository;
    private final AuthorMapper mapper;


    public AuthorService (AuthorRepository repository, AuthorMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
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
}
