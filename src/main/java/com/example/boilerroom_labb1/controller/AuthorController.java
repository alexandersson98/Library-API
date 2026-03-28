package com.example.boilerroom_labb1.controller;


import com.example.boilerroom_labb1.dto.author.AuthorRequestDto;
import com.example.boilerroom_labb1.dto.author.AuthorResponseDto;
import com.example.boilerroom_labb1.dto.book.BookResponseDto;
import com.example.boilerroom_labb1.openapi.BadRequestResponse;
import com.example.boilerroom_labb1.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {
    private final AuthorService service;

    public AuthorController (AuthorService service){
        this.service = service;
    }

    @Operation(summary = "Create author",
            description = "Add an author to the database"
    )
    @ApiResponse(responseCode = "201", description = "Created")
    @BadRequestResponse
    @PostMapping
    public ResponseEntity<AuthorResponseDto>create(@Valid @RequestBody AuthorRequestDto request){
        AuthorResponseDto response = service.createEntity(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto>getAuthorById(@PathVariable Long id){
        AuthorResponseDto response = service.getAuthorById(id);
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/{authorId}/books")
    public ResponseEntity<List<BookResponseDto>> getAllBooksByAuthorId(@PathVariable Long authorId) {
        return ResponseEntity.ok(service.getBooksByAuthor(authorId));
    }
}
