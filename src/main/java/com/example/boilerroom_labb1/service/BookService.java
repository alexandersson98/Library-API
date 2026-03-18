package com.example.boilerroom_labb1.service;


import com.example.boilerroom_labb1.dto.BookRequestDto;
import com.example.boilerroom_labb1.dto.BookResponseDto;
import com.example.boilerroom_labb1.dto.BookResponseDtoV2;
import com.example.boilerroom_labb1.dto.BookWrapperDtoV2;
import com.example.boilerroom_labb1.entity.Book;
import com.example.boilerroom_labb1.exceptions.BookNotFoundException;
import com.example.boilerroom_labb1.mapper.BookMapper;
import com.example.boilerroom_labb1.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;
    private final BookMapper mapper;



    public BookService(BookRepository repository,
    BookMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public Book createEntity(BookRequestDto request){
        Book book = mapper.toEntity(request);
        return repository.save(book);
    }

    public BookResponseDto createBook(BookRequestDto request){
        Book saved = createEntity(request);
        return mapper.toResponseDto(saved);
    }
    public BookResponseDtoV2 createBookV2(BookRequestDto request){
        Book saved = createEntity(request);
        return mapper.toResponseDtoV2(saved);
    }

    public BookResponseDto getBookById(Long id){
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public BookWrapperDtoV2 getBookByIdV2(Long id) {
        BookResponseDtoV2 dto  = repository.findById(id)
                .map(mapper::toResponseDtoV2)
                .orElseThrow(() -> new BookNotFoundException(id));
        return new BookWrapperDtoV2(List.of(dto), "V2");
    }

    public BookWrapperDtoV2 getAll() {
        List<BookResponseDtoV2> books = repository.findAll()
                .stream()
                .map(mapper::toResponseDtoV2)
                .toList();
        return new BookWrapperDtoV2(books, "V2");
    }
    public List<BookResponseDtoV2>getAllV2(){
        List<Book>all = repository.findAll();
        return all.stream()
                .map(mapper::toResponseDtoV2)
                .toList();
    }
}
