package com.example.bookshop.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
@Setter
public class BookNotFoundException extends RuntimeException {
    private String message;

    public BookNotFoundException(String message) {
        this.message = message;
    }

}