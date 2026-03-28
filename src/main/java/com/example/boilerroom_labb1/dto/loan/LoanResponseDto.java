package com.example.boilerroom_labb1.dto.loan;

import com.example.boilerroom_labb1.dto.book.BookResponseDto;

import java.time.LocalDate;

public record LoanResponseDto(Long id,
                              Long bookId,
                              String bookTitle,
                              LocalDate loanDate,
                              LocalDate returnDate) {
}
