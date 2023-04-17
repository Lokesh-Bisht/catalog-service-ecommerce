package dev.lokeshbisht.catalogservice.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {

  private static final Long serialVersionID = 1L;

  public ProductAlreadyExistsException(String message) {
    super(message);
  }
}
