package com.lambdaschool.schools.handlers;



import com.lambdaschool.schools.exceptions.NoHandlerFoundException;
import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.ErrorDetails;
import com.lambdaschool.schools.services.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    HelperFunctions helperFunctions;

    public RestExceptionHandler()
    {
        super();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe){
        ErrorDetails errorDetails= new ErrorDetails();

        errorDetails.setTimestamp(new Date());
        errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetails.setTitle("Resource Not Found");
        errorDetails.setDetail(rnfe.getMessage());
        errorDetails.setDeveloperMessage(rnfe.getClass().getName());
        errorDetails.setError(helperFunctions.getConstraintViolations(rnfe));

        return new ResponseEntity<>(errorDetails, null,HttpStatus.NOT_FOUND);
    }
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<?> handleResourceFoundException(NoHandlerFoundException rfe){
//        ErrorDetails errorDetails = new ErrorDetails();
//        errorDetails.setTimestamp(new Date());
//        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
//        errorDetails.setTitle("Rest Internal Exception");
//        errorDetails.setDetail(rfe.getMessage());
//        errorDetails.setDeveloperMessage(rfe.getClass().getName());
//        errorDetails.setError(helperFunctions.getConstraintViolations(rfe));
//
//        return new ResponseEntity<>(errorDetails, null ,HttpStatus.BAD_REQUEST);
//    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setTimestamp(new Date());
        errorDetails.setStatus(status.value());
        errorDetails.setTitle("Rest Internal Exception");
        errorDetails.setDetail(ex.getMessage());
        errorDetails.setDeveloperMessage(ex.getClass().getName());
        errorDetails.setError(helperFunctions.getConstraintViolations(ex));

        return new ResponseEntity<>(errorDetails, null ,status);
    }

//    @Override
//    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails();
//
//        errorDetails.setTimestamp(new Date());
//        errorDetails.setStatus(status.value());
//        errorDetails.setTitle("Rest Internal Exception");
//        errorDetails.setDetail(ex.getMessage());
//        errorDetails.setDeveloperMessage(ex.getClass().getName());
//        errorDetails.setError(helperFunctions.getConstraintViolations(ex));
//
//        return new ResponseEntity<>(errorDetails, null ,status);
//    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(org.springframework.web.servlet.NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setTimestamp(new Date());
        errorDetails.setStatus(status.value());
        errorDetails.setTitle("Rest Internal Exception");
        errorDetails.setDetail(request.getDescription(false));
        errorDetails.setDeveloperMessage(ex.getClass().getName());
        errorDetails.setError(helperFunctions.getConstraintViolations(ex));

        return new ResponseEntity<>(errorDetails, null ,status);
    }
}
