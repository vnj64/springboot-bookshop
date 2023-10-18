package com.example.bookshop.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorNotFoundException extends RuntimeException {
    private String message;

    public AuthorNotFoundException(String message) {
        this.message = message;
    }
}
