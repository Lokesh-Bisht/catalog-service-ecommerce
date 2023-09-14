package dev.lokeshbisht.catalogservice.exceptions;

public class BadRequestException extends RuntimeException {

    private static final Long serialVersionID = 1L;

    public BadRequestException(String message) {
        super(message);
    }
}
