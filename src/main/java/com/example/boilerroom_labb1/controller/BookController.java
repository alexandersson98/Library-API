package com.example.boilerroom_labb1.controller;


import com.example.boilerroom_labb1.dto.BookRequestDto;
import com.example.boilerroom_labb1.dto.BookResponseDto;
import com.example.boilerroom_labb1.dto.BookResponseDtoV2;
import com.example.boilerroom_labb1.dto.BookWrapperDtoV2;
import com.example.boilerroom_labb1.entity.Book;
import com.example.boilerroom_labb1.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create book",
            description = "Add a book to the database")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<BookResponseDto> create(@RequestBody BookRequestDto request) {
        BookResponseDto response = service.createBook(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }



    @Operation(summary = "Get a book with id",
            description = "Returns a book with given id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id){
        BookResponseDto response = service.getBookById(id);
        return ResponseEntity
                .ok()
                .body(response);
    }
    @Operation(summary = "Get all books",
            description = "Returns a list of all books")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity <List<BookResponseDto>> getBooks(){
        return ResponseEntity.ok(service.getAll());
    }
}
