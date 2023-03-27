package com.smile.miniged.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such order")
public class NotFound extends RuntimeException{
    public NotFound(String message){
        super(message);
    }
}
