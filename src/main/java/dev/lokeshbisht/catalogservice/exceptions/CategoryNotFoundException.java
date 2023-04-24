package dev.lokeshbisht.catalogservice.exceptions;

public class CategoryNotFoundException extends RuntimeException {

  private static final long serialVersionID = 1L;

  public CategoryNotFoundException(String message) {
    super(message);
  }
}
