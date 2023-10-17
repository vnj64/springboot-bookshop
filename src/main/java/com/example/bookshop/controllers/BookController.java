package com.example.bookshop.controllers;

import com.example.bookshop.exception.BookAlreadyExistsException;
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
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        try {
            Book createdBook = bookService.saveBook(book);
            return ResponseEntity.ok(createdBook);
        } catch (BookAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Книга с таким порядковым номером уже существует.");
        }
    }



    @PutMapping(value = "/update/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable Long bookId, Book updatedBook) {
        Book updated = bookService.updateBook(bookId, updatedBook);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Книги с ID: " + bookId + " не существует");
        }
    }

    @DeleteMapping(value = "/delete/{bookId}")
    public ResponseEntity deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().body("Книга с ID: "+bookId+" успешно удалена.");
    }

}
