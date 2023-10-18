package com.example.bookshop.controllers;

import com.example.bookshop.exception.AuthorNotFoundException;
import com.example.bookshop.models.Author;
import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;


    @GetMapping("/all")
    public ResponseEntity<List<Author>> allAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author createdAuthor = authorService.createAuthor(author);
        return ResponseEntity.ok(createdAuthor);
    }

    @PutMapping(value = "/update/{authorId}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long authorId, @RequestBody Author updatedAuthor) {
        try {
            Author updatedAuthorEntity = authorService.updateAuthor(updatedAuthor, authorId);
            return ResponseEntity.ok(updatedAuthorEntity);
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Автор с ID: " + authorId + " не найден.");
        }
    }
}
