package dev.lokeshbisht.catalogservice.exceptions;

public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionID = 1L;

    public ProductNotFoundException(String message) {
        super(message);
    }
}
