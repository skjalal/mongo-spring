package com.example.exception;

import com.example.dto.ErrorDTO;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = {RestaurantNotFound.class})
  public ErrorDTO handle(RestaurantNotFound ex) {
    log.error("Data not found", ex);
    var error = new ErrorDTO();
    error.setCode(404);
    error.setMessage(ex.getLocalizedMessage());
    error.setTimestamp(LocalDateTime.now());
    return error;
  }

  @ExceptionHandler(value = {Exception.class})
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorDTO handle(Exception ex) {
    log.error("Global exception", ex);
    var error = new ErrorDTO();
    error.setCode(500);
    error.setMessage(ex.getLocalizedMessage());
    error.setTimestamp(LocalDateTime.now());
    return error;
  }
}
