package com.example.bookshop.controllers;

import com.example.bookshop.exception.BookAlreadyExistsException;
import com.example.bookshop.exception.BookNotFoundException;
import com.example.bookshop.models.Author;
import com.example.bookshop.models.Book;
import com.example.bookshop.repositories.BookRepository;
import com.example.bookshop.services.AuthorService;
import com.example.bookshop.services.BookService;
import liquibase.pro.packaged.P;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    public BookController(BookService bookService, BookRepository bookRepository, AuthorService authorService) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @GetMapping(value = "/all")
    public List<Book> getBooks(
            @RequestParam(name = "isOnShelf", required = false) boolean isOnShelf,
            @RequestParam(name = "sortType", required = false) String sortType) {
        if (Boolean.TRUE.equals(isOnShelf)) {
            return bookService.getAllBooksOnShelf(true);
        } else if ("seqNum".equals(sortType)) {
            return bookService.getAllBooksBySeqNum();
        } else if ("bookName".equals(sortType)) {
            return bookService.getAllBooksByBookName();
        }
        return bookService.getAllBooks();
    }

    @GetMapping(value = "/byAuthor/{authorId}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable Long authorId) {
        Author author = authorService.getAuthorById(authorId);
        if (author != null) {
            return ResponseEntity.ok(bookService.getBooksByAuthor(author));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        try {
            Book createdBook = bookService.saveBook(book);
            return ResponseEntity.ok(createdBook);
        } catch (BookAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Книга с таким порядковым номером уже существует.");
        }
    }



    @PutMapping(value = "/update/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
        try {
            Book updatedBookEntity = bookService.updateBook(bookId, updatedBook);
            return ResponseEntity.ok(updatedBookEntity);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Автор с ID: " + bookId + " не найден.");
        }
    }

    @DeleteMapping(value = "/delete/{bookId}")
    public ResponseEntity deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().body("Книга с ID: "+bookId+" успешно удалена.");
    }

}
