package com.example.boilerroom_labb1.controller;


import com.example.boilerroom_labb1.dto.BookRequestDto;
import com.example.boilerroom_labb1.dto.BookResponseDto;
import com.example.boilerroom_labb1.dto.BookResponseDtoV2;
import com.example.boilerroom_labb1.dto.BookWrapperDtoV2;
import com.example.boilerroom_labb1.entity.Book;
import com.example.boilerroom_labb1.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService service;


    public BookController(BookService service){
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<BookResponseDto> create(@RequestBody BookRequestDto request) {
        BookResponseDto response = service.createBook(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }




    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id){
        BookResponseDto response = service.getBookById(id);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping
    public ResponseEntity <List<BookResponseDto>> getBooks(){
        return ResponseEntity.ok(service.getAll());
    }
}
