package dev.lokeshbisht.catalogservice.controller;

import dev.lokeshbisht.catalogservice.dto.ErrorResponseDto;
import dev.lokeshbisht.catalogservice.entity.Product;
import dev.lokeshbisht.catalogservice.enums.ErrorCode;
import dev.lokeshbisht.catalogservice.exceptions.BadRequestException;
import dev.lokeshbisht.catalogservice.exceptions.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponseDto> handleBadRequestException(BadRequestException ex) {
    logger.error("BadRequestException occurred due to: {}", ex.getMessage());
    ErrorResponseDto errorResponseDto = new ErrorResponseDto(ErrorCode.BAD_REQUEST, ex.getMessage());
    return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleProductNotFoundException(ProductNotFoundException ex) {
    logger.error("ProductNotFoundException: {}", ex.getMessage());
    ErrorResponseDto errorResponseDto = new ErrorResponseDto(ErrorCode.PRODUCT_NOT_FOUND, ex.getMessage());
    return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDto> handleAllException(Exception ex) {
    UUID uuid = UUID.randomUUID();
    String message = String.format("Unhandled exception, logged against error id: %s", uuid);
    logger.error("Exception: {} {}", message, ex.getClass().getName(), ex);
    logger.error(ex.getMessage(), ex);
    ErrorResponseDto errorResponseDto = new ErrorResponseDto(ErrorCode.INTERNAL_SERVER_ERROR, message);
    return new ResponseEntity<>(errorResponseDto, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
