package com.example.boilerroom_labb1.repository;

import com.example.boilerroom_labb1.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    boolean existsByBookId(Long bookId);
    List<Loan> findByReturnDateIsNull();
}
