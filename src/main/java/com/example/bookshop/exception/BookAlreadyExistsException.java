package com.example.bookshop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class BookAlreadyExistsException  extends RuntimeException {

    private String message;

    public BookAlreadyExistsException(String message) {
        super(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
