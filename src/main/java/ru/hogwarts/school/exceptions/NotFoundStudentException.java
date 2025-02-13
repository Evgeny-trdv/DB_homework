package ru.hogwarts.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundStudentException extends RuntimeException{
    public NotFoundStudentException() {
    }

    public NotFoundStudentException(String message) {
        super(message);
    }

    public NotFoundStudentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundStudentException(Throwable cause) {
        super(cause);
    }

    public NotFoundStudentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
