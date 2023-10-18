package com.example.bookshop.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class BookAlreadyExistsException  extends RuntimeException {

    private String message;

    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
