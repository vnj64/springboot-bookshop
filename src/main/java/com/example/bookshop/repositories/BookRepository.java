package com.example.bookshop.repositories;

import com.example.bookshop.models.Author;
import com.example.bookshop.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(Author author);
}
