package com.example.boilerroom_labb1.controller;


import com.example.boilerroom_labb1.dto.author.EditBookRequestDto;
import com.example.boilerroom_labb1.dto.book.BookRequestDto;
import com.example.boilerroom_labb1.dto.book.BookResponseDto;
import com.example.boilerroom_labb1.dto.loan.LoanResponseDto;
import com.example.boilerroom_labb1.entity.Author;
import com.example.boilerroom_labb1.openapi.BadRequestResponse;
import com.example.boilerroom_labb1.openapi.NotFoundResponse;
import com.example.boilerroom_labb1.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    })
    @BadRequestResponse
    @PostMapping
    public ResponseEntity<BookResponseDto> create(@Valid @RequestBody BookRequestDto request) {
        BookResponseDto response = service.createBook(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(summary = "Get a book with id",
            description = "Returns a book with given id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found"),
    })
    @NotFoundResponse
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

    @Operation(summary = "Edit book",
            description = "Edit a book")
    @PatchMapping("/edit/{id}")
    public ResponseEntity<BookResponseDto>editBook(@PathVariable Long id, @RequestBody EditBookRequestDto editBookRequestDto){
        BookResponseDto response = service.editBook(id, editBookRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
