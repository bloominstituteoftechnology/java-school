package com.lambdaschool.schools.handlers;

import com.lambdaschool.schools.exceptions.ResourceFoundException;
import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.ErrorDetail;
import com.lambdaschool.schools.services.HelperFunctions;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private HelperFunctions helperFunctions;

    SimpleDateFormat dateFormat = new SimpleDateFormat("E, MMM dd,yyyy || HH:mm:ss");
    Date date = new Date();

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe) {

    ErrorDetail errorDetail = new ErrorDetail();

    errorDetail.setTitle("Resource Not Found!");
    errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
    errorDetail.setDetail(rnfe.getMessage());
    errorDetail.setTimestamp(dateFormat.format(date));
    errorDetail.setDeveloperMessage(rnfe.getClass().getName());
    errorDetail.setErrors(helperFunctions.getValidationErrors(rnfe));
    return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ResourceFoundException.class)
  public ResponseEntity<?> handleResourceFoundException(ResourceFoundException rsfe) {

    ErrorDetail confirmDetail = new ErrorDetail();

    confirmDetail.setTitle("Resource Found!");
    confirmDetail.setStatus(HttpStatus.FOUND.value());
    confirmDetail.setDetail(rsfe.getMessage());
    confirmDetail.setTimestamp(dateFormat.format(date));
    confirmDetail.setDeveloperMessage("Found an issue with School: " + rsfe.getClass().getName());
    confirmDetail.setErrors(helperFunctions.getValidationErrors(rsfe));
    return new ResponseEntity<>(confirmDetail, null, HttpStatus.FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                           HttpHeaders headers, HttpStatus status,
                                                           WebRequest request) {

    ErrorDetail errorDetail = new ErrorDetail();

    errorDetail.setTitle("Rest Internal Exception");
    errorDetail.setStatus(status.value());
    errorDetail.setDetail(ex.getMessage());
    errorDetail.setTimestamp(dateFormat.format(date));
    errorDetail.setDeveloperMessage("Found an issue with School: " + ex.getClass().getName());
    errorDetail.setErrors(helperFunctions.getValidationErrors(ex));
    return new ResponseEntity<>(errorDetail, null, status);
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    ErrorDetail errorDetail = new ErrorDetail();

    errorDetail.setTitle("Rest Internal Exception");
    errorDetail.setStatus(status.value());
    errorDetail.setDetail(ex.getMessage());
    errorDetail.setTimestamp(dateFormat.format(date));
    errorDetail.setDeveloperMessage("Found an issue with School: " + ex.getClass().getName());
    errorDetail.setErrors(helperFunctions.getValidationErrors(ex));
    return new ResponseEntity<>(errorDetail, null, status);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    ErrorDetail errorDetail = new ErrorDetail();

    errorDetail.setTitle("Rest Internal Exception");
    errorDetail.setStatus(status.value());
    errorDetail.setDetail("Found an issue with School: " + ex.getMessage());
    errorDetail.setTimestamp(dateFormat.format(date));
    errorDetail.setDeveloperMessage(ex.getClass().getName());
    errorDetail.setErrors(helperFunctions.getValidationErrors(ex));
    return new ResponseEntity<>(errorDetail, null, status);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(
      NoHandlerFoundException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    ErrorDetail errorDetail = new ErrorDetail();

    errorDetail.setTitle("Rest Internal Exception");
    errorDetail.setStatus(status.value());
    errorDetail.setDetail("Found an issue with School: " + ex.getMessage());
    errorDetail.setTimestamp(dateFormat.format(date));
    errorDetail.setDeveloperMessage(ex.getClass()
                                      .getName());
    errorDetail.setErrors(helperFunctions.getValidationErrors(ex));
    return new ResponseEntity<>(errorDetail, status);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    ErrorDetail errorDetail = new ErrorDetail();

    errorDetail.setTitle("Rest Internal Exception");
    errorDetail.setStatus(status.value());
    errorDetail.setDetail("Found an issue with School: " + ex.getMessage());
    errorDetail.setTimestamp(dateFormat.format(date));
    errorDetail.setDeveloperMessage(ex.getClass()
                                      .getName());
    errorDetail.setErrors(helperFunctions.getValidationErrors(ex));
    return new ResponseEntity<>(errorDetail, status);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(
      TypeMismatchException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    ErrorDetail errorDetail = new ErrorDetail();

    errorDetail.setTitle("Rest Internal Exception");
    errorDetail.setStatus(status.value());
    errorDetail.setDetail("Found an issue with School: " + ex.getMessage());
    errorDetail.setTimestamp(dateFormat.format(date));
    errorDetail.setDeveloperMessage(ex.getClass()
                                      .getName());
    errorDetail.setErrors(helperFunctions.getValidationErrors(ex));
    return new ResponseEntity<>(errorDetail, status);
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(
      MissingPathVariableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    ErrorDetail errorDetail = new ErrorDetail();

    errorDetail.setTitle("Rest Internal Exception");
    errorDetail.setStatus(status.value());
    errorDetail.setDetail("Found an issue with School: " + ex.getMessage());
    errorDetail.setTimestamp(dateFormat.format(date));
    errorDetail.setDeveloperMessage(ex.getClass()
                                      .getName());
    errorDetail.setErrors(helperFunctions.getValidationErrors(ex));
    return new ResponseEntity<>(errorDetail, status);
  }
}