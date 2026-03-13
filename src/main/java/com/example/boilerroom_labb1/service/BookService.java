package com.example.boilerroom_labb1.service;


import com.example.boilerroom_labb1.dto.CreateBookRequest;
import com.example.boilerroom_labb1.entity.Book;
import com.example.boilerroom_labb1.exceptions.BookNotFoundException;
import com.example.boilerroom_labb1.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;


    public BookService(BookRepository repository){
        this.repository = repository;
    }

    public Book create(CreateBookRequest request){
        Book book = new Book(
                request.getTitle(),
                request.getAuthor(),
                request.getIsbn(),
                request.getPublishedYear()
        );
        return repository.save(book);
    }

    public Book getBookById(Long id){
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

    }

    public List<Book>getAll(){
        return repository.findAll();
    }
}
