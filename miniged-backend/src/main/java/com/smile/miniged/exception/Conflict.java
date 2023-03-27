package com.smile.miniged.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Conflict error")
public class Conflict extends RuntimeException{
    public Conflict(String message){
        super(message);
    }
}
