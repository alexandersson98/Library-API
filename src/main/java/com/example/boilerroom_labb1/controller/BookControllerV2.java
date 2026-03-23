package com.example.boilerroom_labb1.controller;


import com.example.boilerroom_labb1.dto.BookRequestDto;
import com.example.boilerroom_labb1.dto.BookResponseDtoV2;
import com.example.boilerroom_labb1.dto.BookWrapperDtoV2;
import com.example.boilerroom_labb1.openapi.BadRequestResponse;
import com.example.boilerroom_labb1.openapi.NotFoundResponse;
import com.example.boilerroom_labb1.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v2/books")
public class BookControllerV2 {
    private final BookService service;



    public BookControllerV2(BookService service){
        this.service = service;
    }

    @Operation(summary = "Create book",
    description = "Add a book to the database")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
    })
    @BadRequestResponse
    @PostMapping
    public ResponseEntity<BookResponseDtoV2> create(@Valid @RequestBody BookRequestDto request) {
       BookResponseDtoV2 response = service.createBookV2(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(summary = "Get a book with id",
    description = "Using version2 returns a book with given id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found"),
    })
    @NotFoundResponse
    @GetMapping("/{id}")
    public ResponseEntity<BookWrapperDtoV2>getBookById(Long id){
        BookWrapperDtoV2 response = service.getBookByIdV2(id);
        return ResponseEntity
                .ok()
                .body(response);
    }
    @Operation(summary = "Get all books",
    description = "Using version2 returns a wrapped list of all books")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity <BookWrapperDtoV2>getAllV2() {
        BookWrapperDtoV2 response = service.getAllV2();
        return ResponseEntity
                .ok()
                .body(response);
    }
}
