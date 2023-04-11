package dev.lokeshbisht.catalogservice.exceptions;

public class BrandNotFoundException extends RuntimeException {

  private static final Long serialVersionID = 1L;

  public BrandNotFoundException(String message) {
    super(message);
  }
}
