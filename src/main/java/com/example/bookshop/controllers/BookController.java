package com.example.bookshop.controllers;

import com.example.bookshop.models.Author;
import com.example.bookshop.models.Book;
import com.example.bookshop.repositories.BookRepository;
import com.example.bookshop.services.AuthorService;
import com.example.bookshop.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
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
    @GetMapping(value = "/sorted/byName")
    public List<Book> getBooksByName(@RequestParam(name = "bookName", required = false) String bookName) {
        return bookService.getBooksByName(bookName);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createBook(@RequestBody Book book) throws IOException {
        try {
            Book createdBook = bookService.saveBook(book);
            return ResponseEntity.ok(createdBook);
        } catch (Exception e) {
            throw e;
        }
    }
    @PutMapping(value = "/update/book/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, Book updatedBook) {
        Book updated = bookService.updateBook(bookId, updatedBook);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        Book book = bookService.findBookById(bookId);
        if (book != null) {
            bookService.deleteBook(bookId);
            return "Книга успешно удалена!";
        } else {
            return "Книга не найдена :(";
        }
    }

}
