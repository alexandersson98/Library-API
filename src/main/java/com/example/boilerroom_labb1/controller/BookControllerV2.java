package com.example.boilerroom_labb1.controller;


import com.example.boilerroom_labb1.dto.BookRequestDto;
import com.example.boilerroom_labb1.dto.BookResponseDto;
import com.example.boilerroom_labb1.dto.BookResponseDtoV2;
import com.example.boilerroom_labb1.dto.BookWrapperDtoV2;
import com.example.boilerroom_labb1.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/books")
public class BookControllerV2 {
    private final BookService service;



    public BookControllerV2(BookService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BookResponseDtoV2> create(@RequestBody BookRequestDto request) {
       BookResponseDtoV2 response = service.createBookV2(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/id")
    public ResponseEntity<BookWrapperDtoV2>getBookById(Long id){
        BookWrapperDtoV2 response = service.getBookByIdV2(id);
        return ResponseEntity
                .ok()
                .body(response);
    }
    @GetMapping
    public ResponseEntity <BookWrapperDtoV2>getAll() {
        BookWrapperDtoV2 response = service.getAll();
        return ResponseEntity
                .ok()
                .body(response);
    }
}
