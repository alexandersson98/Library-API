package com.example.boilerroom_labb1.integration;


import com.example.boilerroom_labb1.dto.author.AuthorRequestDto;
import com.example.boilerroom_labb1.dto.author.AuthorResponseDto;
import com.example.boilerroom_labb1.dto.book.BookRequestDto;
import com.example.boilerroom_labb1.dto.book.BookResponseDto;
import com.example.boilerroom_labb1.dto.loan.LoanRequestDto;
import com.example.boilerroom_labb1.dto.loan.LoanResponseDto;
import com.example.boilerroom_labb1.repository.AuthorRepository;
import com.example.boilerroom_labb1.repository.BookRepository;
import com.example.boilerroom_labb1.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class LoanControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    LoanRepository loanRepository;

    @BeforeEach
    void setUp() {
        authorRepository.deleteAll();
        bookRepository.deleteAll();
        loanRepository.deleteAll();
    }

    @Test
    void shouldCreateLoan() {
        AuthorRequestDto authorRequest = new AuthorRequestDto("Joel Göransson");

        ResponseEntity<AuthorResponseDto> authorResponse = restTemplate.postForEntity("/api/v1/author",
                authorRequest,
                AuthorResponseDto.class);

        Long authorId = authorResponse.getBody().id();

        BookRequestDto bookRequest = new BookRequestDto("Peaky Blinders", authorId, "eeee", 2006);
        ResponseEntity<BookResponseDto> bookResponse = restTemplate.postForEntity("/api/v1/books",
                bookRequest,
                BookResponseDto.class);

        Long bookId = bookResponse.getBody().id();

        LoanRequestDto loanRequest = new LoanRequestDto(bookId);
        ResponseEntity<LoanResponseDto> loanResponse = restTemplate.postForEntity("/api/v1/loans",
                loanRequest,
                LoanResponseDto.class);

        assertEquals(HttpStatus.CREATED, loanResponse.getStatusCode());
        assertNotNull(loanResponse.getBody());
        assertNotNull(loanResponse.getBody().id());
    }

    @Test
    void shouldReturnAllLoans() {
        AuthorRequestDto authorRequest = new AuthorRequestDto("Joel Göransson");

        ResponseEntity<AuthorResponseDto> authorResponse = restTemplate.postForEntity("/api/v1/author",
                authorRequest,
                AuthorResponseDto.class);

        Long authorId = authorResponse.getBody().id();

        BookRequestDto bookRequest = new BookRequestDto("Peaky Blinders", authorId, "eeee", 2006);
        ResponseEntity<BookResponseDto> bookResponse = restTemplate.postForEntity("/api/v1/books",
                bookRequest,
                BookResponseDto.class);

        Long bookId = bookResponse.getBody().id();

        LoanRequestDto loanRequest = new LoanRequestDto(bookId);
        ResponseEntity<LoanResponseDto> loanResponse = restTemplate.postForEntity("/api/v1/loans",
                loanRequest,
                LoanResponseDto.class);


        ResponseEntity<LoanResponseDto[]> activeLoans = restTemplate.getForEntity("/api/v1/loans",
                LoanResponseDto[].class);

        LoanResponseDto[] loanResponseDto = activeLoans.getBody();
        assertNotNull(loanResponseDto);
        assertTrue(loanResponseDto.length > 0);

        assertEquals(HttpStatus.CREATED, loanResponse.getStatusCode());
        assertEquals(HttpStatus.OK, activeLoans.getStatusCode());

        LoanResponseDto[] loanResponseDtos = activeLoans.getBody();
        assertNotNull(loanResponseDtos);
        assertTrue(loanResponseDtos.length > 0);

        LoanResponseDto firstLoan = loanResponseDtos[0];
        assertNotNull(firstLoan);
    }

    @Test
    void shouldReturn404WhenCreateLoanAndBookNotFound() {
        Long bookId = 999L;
        LoanRequestDto loanRequest = new LoanRequestDto(bookId);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/loans",
                loanRequest,
                String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
