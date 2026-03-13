package com.example.boilerroom_labb1.controller;


import com.example.boilerroom_labb1.dto.BookDto;
import com.example.boilerroom_labb1.dto.CreateBookRequest;
import com.example.boilerroom_labb1.entity.Book;
import com.example.boilerroom_labb1.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;


    public BookController(BookService service){
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody CreateBookRequest request){
        Book book =  service.create(request);

        BookDto dto = new BookDto(
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublishedYear()
        );
                return ResponseEntity
                        .created(URI.create("/books/" + book.getId()))
                        .body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
            Book book = service.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(){
        return ResponseEntity.ok(service.getAll());
    }
}
