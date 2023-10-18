package com.example.bookshop.services;

import com.example.bookshop.exception.AuthorNotFoundException;
import com.example.bookshop.models.Author;
import com.example.bookshop.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }

    public Author updateAuthor(Author updatedAuthor, Long authorId) {
        Author existingAuthor = authorRepository.findById(authorId).orElse(null);
        if (existingAuthor == null) {
            throw new AuthorNotFoundException("Автор с ID: " + updatedAuthor.getId() + " не найден.");
        }
        existingAuthor.setFirstName(updatedAuthor.getFirstName());
        existingAuthor.setPatronymic(updatedAuthor.getPatronymic());
        existingAuthor.setSurname(updatedAuthor.getSurname());
        existingAuthor.setBirthDate(updatedAuthor.getBirthDate());
        existingAuthor.setBiography(updatedAuthor.getBiography());


        return authorRepository.save(updatedAuthor);
    }
}
