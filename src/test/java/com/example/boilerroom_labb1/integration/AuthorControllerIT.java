package com.example.boilerroom_labb1.integration;


import com.example.boilerroom_labb1.dto.author.AuthorRequestDto;
import com.example.boilerroom_labb1.dto.author.AuthorResponseDto;
import com.example.boilerroom_labb1.repository.AuthorRepository;
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
public class AuthorControllerIT {


    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private AuthorRepository authorRepository;


    @BeforeEach
    void setUp() {
        authorRepository.deleteAll();
    }


    @Test
    void shouldCreateAuthor(){
        AuthorRequestDto authorRequest = new AuthorRequestDto("Herbert Bengtsson");
        ResponseEntity<AuthorResponseDto>authorResponse = restTemplate.postForEntity("/api/v1/author",
                authorRequest,
                AuthorResponseDto.class);


        assertEquals(HttpStatus.CREATED, authorResponse.getStatusCode());
        AuthorResponseDto body = authorResponse.getBody();
        assertNotNull(body);
        assertNotNull(body.id());
        assertEquals("Herbert Bengtsson", body.name());
    }

    @Test
    void shouldReturnAuthorWithId(){
        AuthorRequestDto authorRequest = new AuthorRequestDto("Herbert Bengtsson");
        ResponseEntity<AuthorResponseDto>authorResponse = restTemplate.postForEntity("/api/v1/author",
                authorRequest,
                AuthorResponseDto.class);

        Long authorId = authorResponse.getBody().id();
        ResponseEntity<AuthorResponseDto>getById = restTemplate.getForEntity("/api/v1/author/{id}",
                AuthorResponseDto.class,
                authorId);

        assertEquals(HttpStatus.OK, getById.getStatusCode());
        AuthorResponseDto fetchedBody = getById.getBody();
        assertNotNull(fetchedBody);
        assertEquals("Herbert Bengtsson",fetchedBody.name());
        assertEquals(authorResponse.getBody().id(), fetchedBody.id());


    }

}
