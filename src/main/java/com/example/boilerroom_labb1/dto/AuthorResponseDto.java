package com.example.boilerroom_labb1.dto;


import com.example.boilerroom_labb1.entity.Book;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Response object representing an author")
public record AuthorResponseDto(

        @Schema(description = "Unique identifier of the author", example = "1")
        Long id,

        @Schema(description = "name of the author", example = "Jk Rowling")
        String name,

        @Schema(description = "List of the authors listed books", example = "Harry Potter 1, Harry Potter 2")
        List<BookResponseDto>books
) {
}
