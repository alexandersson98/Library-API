package com.example.boilerroom_labb1.service;

import com.example.boilerroom_labb1.dto.author.EditBookRequestDto;
import com.example.boilerroom_labb1.dto.book.BookRequestDto;
import com.example.boilerroom_labb1.dto.book.BookResponseDto;
import com.example.boilerroom_labb1.dto.book.BookResponseDtoV2;
import com.example.boilerroom_labb1.dto.book.BookWrapperDtoV2;
import com.example.boilerroom_labb1.entity.Author;
import com.example.boilerroom_labb1.entity.Book;
import com.example.boilerroom_labb1.exceptions.NotFoundWithIdException;
import com.example.boilerroom_labb1.exceptions.ResourceNotFoundException;
import com.example.boilerroom_labb1.exceptions.ValidationException;
import com.example.boilerroom_labb1.mapper.BookMapper;
import com.example.boilerroom_labb1.repository.AuthorRepository;
import com.example.boilerroom_labb1.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;
    private final BookMapper mapper;
    private final AuthorRepository authorRepository;


    public BookService(BookRepository repository,
    BookMapper mapper, AuthorRepository authorRepository){
        this.repository = repository;
        this.mapper = mapper;
        this.authorRepository = authorRepository;

    }

    public Book createEntity(BookRequestDto request){
        Author author = authorRepository.findById(request.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + request.authorId() + "not found"));

        Book book = mapper.toEntity(request);
        book.setAuthor(author);
        if (request.publishedYear() <= 1700) {
            throw new ValidationException("Published year must be greater than 1700");
        }
        return repository.save(book);
    }

    public BookResponseDto createBook(BookRequestDto request){
        Book saved = createEntity(request);
        return mapper.toResponseDto(saved);
    }
    public BookResponseDtoV2 createBookV2(BookRequestDto request){
        Book saved = createEntity(request);
        return mapper.toResponseDtoV2(saved);
    }

    public BookResponseDto getBookById(Long id){
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new NotFoundWithIdException("Book not found with id: ", + id));
    }

    public BookWrapperDtoV2 getBookByIdV2(Long id) {
        BookResponseDtoV2 dto  = repository.findById(id)
                .map(mapper::toResponseDtoV2)
                .orElseThrow(() -> new NotFoundWithIdException("Book not found with id: ", + id));
        return new BookWrapperDtoV2(List.of(dto), "V2");
    }

    public BookWrapperDtoV2 getAllV2() {
        List<BookResponseDtoV2> books = repository.findAll()
                .stream()
                .map(mapper::toResponseDtoV2)
                .toList();
        return new BookWrapperDtoV2(books, "V2");
    }
    public List<BookResponseDto>getAll(){
        List<Book>all = repository.findAll();
        return all.stream()
                .map(mapper::toResponseDto)
                .toList();
    }


    public BookResponseDto editBook(Long id, EditBookRequestDto editBookRequest){
        Book book = repository.findById(id).orElseThrow(() -> new NotFoundWithIdException("Book not found with id: ", + id));
            if(editBookRequest.title() != null){
                book.setTitle(editBookRequest.title());
            }
            if (editBookRequest.authorId() != null ){
                book.setAuthor(authorRepository.findById(editBookRequest.authorId()).orElseThrow(()->new NotFoundWithIdException("Author not found with id: ", + editBookRequest.authorId())));
            }
            if (editBookRequest.isbn() != null) {
                book.setIsbn(editBookRequest.isbn());
            }
            if (editBookRequest.publishedYear() != null) {
                book.setPublishedYear(editBookRequest.publishedYear());
            }
            repository.save(book);
            return mapper.toResponseDto(book);

    }
}
