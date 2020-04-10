package com.panchek.wp.readmore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReviewPermissionException extends RuntimeException{

    public ReviewPermissionException() {
        super(String.format("You do not have the permission to edit this review!"));
    }
}
