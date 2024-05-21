package com.pluralsight.photoclone;

import org.springframework.http.HttpStatus;

public class RepsonseStatusException extends Throwable {
    public RepsonseStatusException(HttpStatus httpStatus) {
    }
}
