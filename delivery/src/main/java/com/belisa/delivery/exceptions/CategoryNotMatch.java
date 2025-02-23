package com.belisa.delivery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryNotMatch extends RuntimeException {
    public CategoryNotMatch(String message) {
        super(message);
    }
}
