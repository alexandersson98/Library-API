package com.example.boilerroom_labb1.repository;

import com.example.boilerroom_labb1.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
