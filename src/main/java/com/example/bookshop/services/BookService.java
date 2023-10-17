package com.example.bookshop.services;

import com.example.bookshop.models.Author;
import com.example.bookshop.models.Book;
import com.example.bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Книга не найдена."));
        bookRepository.delete(book);
    }

    public List<Book> getBooksByAuthor(Author author) {
        return bookRepository.findByAuthor(author);
    }

    public Book updateBook(Long bookId, Book updatedBook) {
        Optional<Book> isBookExist = bookRepository.findById(bookId);
        if (isBookExist.isPresent()) {
            Book existingBook = isBookExist.get();

            existingBook.setBookName(updatedBook.getBookName());
            existingBook.setSeqNum(updatedBook.getSeqNum());
            existingBook.setIsOnShelf(updatedBook.getIsOnShelf());

            if (updatedBook.getAuthor() != null) {
                existingBook.setAuthor(updatedBook.getAuthor());
            }
            return bookRepository.save(updatedBook);
        } else {
            return null;
        }
    }

    public List<Book> getBooksByName(String bookName) {
        return bookRepository.findByBookNameContainingIgnoreCase(bookName);
    }

}
