package com.lambdaschool.schools.handlers;

import com.lambdaschool.schools.exceptions.ResourceFoundException;
import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.ErrorDetail;
import com.lambdaschool.schools.services.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    HelperFunctions helperFunctions;

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setDetail("Found an issue with School: " + ex.getMessage());
        errorDetail.setErrors(helperFunctions.getConstraintViolation(ex));
        errorDetail.setDeveloper(ex.getClass().getName());
        errorDetail.setStatus(status.value());
        errorDetail.setTimestamp(new Date());
        errorDetail.setTitle("Rest Internal Exception");
        return new ResponseEntity<>(errorDetail, headers, status);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex)
    {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setDetail("Found an issue with School: " + ex.getMessage());
        errorDetail.setErrors(helperFunctions.getConstraintViolation(ex));
        errorDetail.setDeveloper(ex.getClass().getName());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTimestamp(new Date());
        errorDetail.setTitle("Resource Not Found.");
        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ResourceFoundException.class)
    public ResponseEntity<?> handleResourceFoundException(ResourceFoundException ex)
    {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setDetail("Found an issue with School: " + ex.getMessage());
        errorDetail.setErrors(helperFunctions.getConstraintViolation(ex));
        errorDetail.setDeveloper(ex.getClass().getName());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setTimestamp(new Date());
        errorDetail.setTitle("Unexpected Resource.");
        return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);

    }

}
