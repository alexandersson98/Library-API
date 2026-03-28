package com.example.boilerroom_labb1.dto.loan;

import java.time.LocalDate;

public record LoanResponseDto(Long id,
                              Long bookId,
                              LocalDate loanDate,
                              LocalDate returnDate) {
}
