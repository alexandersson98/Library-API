package com.example.boilerroom_labb1.service;


import com.example.boilerroom_labb1.dto.loan.LoanRequestDto;
import com.example.boilerroom_labb1.dto.loan.LoanResponseDto;
import com.example.boilerroom_labb1.entity.Book;
import com.example.boilerroom_labb1.entity.Loan;
import com.example.boilerroom_labb1.exceptions.BookAlreadyLoanedException;
import com.example.boilerroom_labb1.exceptions.NotFoundWithIdException;
import com.example.boilerroom_labb1.exceptions.NotFoundException;
import com.example.boilerroom_labb1.exceptions.ValidationException;
import com.example.boilerroom_labb1.mapper.LoanMapper;
import com.example.boilerroom_labb1.repository.BookRepository;
import com.example.boilerroom_labb1.repository.LoanRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private LoanMapper loanMapper;

    public LoanService(LoanRepository loanRepository,
                       BookRepository bookRepository,
                       LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.loanMapper = loanMapper;
    }

    @CacheEvict(value = "loan", allEntries = true)
    @Transactional
    public LoanResponseDto createLoan(LoanRequestDto request){
        Book book = bookRepository.findByIdWithLock(request.bookId())
                .orElseThrow(() -> new NotFoundException("Book not found"));
        if (loanRepository.existsByBookIdAndReturnDateIsNull(book.getId())){
            throw new BookAlreadyLoanedException("Book already loaned.");
        }
        try {

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(null);

        Loan savedLoan = loanRepository.saveAndFlush(loan);

        return loanMapper.toResponseDto(savedLoan);
    } catch (DataIntegrityViolationException e){
        throw new BookAlreadyLoanedException("Book already loaned");}
    }

    @Cacheable("loan")
    public  List<LoanResponseDto> getAllLoans(){
           return loanRepository.findByReturnDateIsNull().stream()
                .map(loanMapper::toResponseDto)
                .toList();
    }

    @CacheEvict(value = "loan", allEntries = true)
    public LoanResponseDto returnBook(Long id){
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new NotFoundWithIdException("Loan not found with id: ", id));
            if(loan.getReturnDate() != null) {
                throw new ValidationException("Loan already returned");
            }
        loan.setReturnDate(LocalDate.now());
            Loan returnedLoan = loanRepository.saveAndFlush(loan);
            return loanMapper.toResponseDto(returnedLoan);
    }
}
