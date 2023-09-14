package dev.lokeshbisht.catalogservice.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException {

    private static final Long serialVersionID = 1L;

    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
