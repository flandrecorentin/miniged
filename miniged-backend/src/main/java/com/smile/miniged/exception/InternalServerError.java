package com.smile.miniged.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal servor error")
public class InternalServerError extends RuntimeException{
    public InternalServerError(String message){
        super(message);
    }
}
