package dev.lokeshbisht.catalogservice.exceptions;

public class BrandAlreadyExistsException extends RuntimeException {

    private static final Long serialVersionID = 1L;

    public BrandAlreadyExistsException(String message) {
        super(message);
    }
}
