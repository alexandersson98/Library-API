package com.example.boilerroom_labb1.integration;


import com.example.boilerroom_labb1.TestData;

import com.example.boilerroom_labb1.dto.book.BookRequestDto;
import com.example.boilerroom_labb1.dto.book.BookResponseDto;
import com.example.boilerroom_labb1.entity.Author;
import com.example.boilerroom_labb1.entity.Book;
import com.example.boilerroom_labb1.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BookControllerIT {
@Autowired private TestRestTemplate restTemplate;
@Autowired private BookRepository bookRepository;
@BeforeEach void setUp(){ bookRepository.deleteAll();}




    @Test
   void shouldReturn201AndSaveBookWhenCreatingValidBook(){
            Author author = TestData.createAuthor("Matt Duffer");


            BookRequestDto request = new BookRequestDto(
                    "Stranger Things",
                    author.getId(),
                    "EV443-FRed",
                    2016
            );

            ResponseEntity<BookResponseDto> response = restTemplate.postForEntity("/api/v1/books",
                    request,
                    BookResponseDto.class);
            assertThat(response.getStatusCode().value()).isEqualTo(201);
            assertThat(response.getBody()).isNotNull();

            assertThat(response.getBody().title()).isEqualTo("Stranger Things");
            assertThat(response.getBody().authorName()).isEqualTo("Matt Duffer");

            assertThat(bookRepository.count()).isEqualTo(1);

            Book savedBook = bookRepository.findAll().get(0);
            assertThat(savedBook.getTitle()).isEqualTo("Stranger Things");
            assertThat(savedBook.getAuthor()).isEqualTo("Matt Duffer");
            assertThat(savedBook.getIsbn()).isEqualTo("EV443-FRed");
            assertThat(savedBook.getPublishedYear()).isEqualTo(2016);
        }

        @Test
        void shouldReturn200AndBookWhenBookExists() {
            Book savedBook = bookRepository.save(
                    new Book(
                            "Flammande Osten",
                            TestData.createAuthor("Osten Bengtsson"),
                            "45f32",
                            1774
                    )
            );

            ResponseEntity<BookResponseDto> response = restTemplate.getForEntity(
                    "/api/v1/books/" + savedBook.getId(),
                    BookResponseDto.class
            );

            assertThat(response.getStatusCode().value()).isEqualTo(200);
            assertThat(response.getBody()).isNotNull();

            assertThat(response.getBody().title()).isEqualTo("Flammande Osten");
            assertThat(response.getBody().authorName()).isEqualTo("Ostsson Bengt");
        }
}


