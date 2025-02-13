package ru.hogwarts.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundFacultyException extends RuntimeException{
    public NotFoundFacultyException() {
    }

    public NotFoundFacultyException(String message) {
        super(message);
    }

    public NotFoundFacultyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundFacultyException(Throwable cause) {
        super(cause);
    }

    public NotFoundFacultyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
