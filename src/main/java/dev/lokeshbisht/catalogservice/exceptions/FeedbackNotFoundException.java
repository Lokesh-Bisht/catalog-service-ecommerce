package dev.lokeshbisht.catalogservice.exceptions;

public class FeedbackNotFoundException extends RuntimeException {

  private static final Long serialVersionID = 1L;

  public FeedbackNotFoundException(String message) {
    super(message);
  }
}
