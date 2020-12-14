package com.lambdaschool.schools.handlers;

import com.lambdaschool.schools.exceptions.MissingException;
import com.lambdaschool.schools.models.ErrorDetails;
import com.lambdaschool.schools.services.HelperFunctions;
import org.apache.coyote.Response;
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

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestException extends ResponseEntityExceptionHandler {
    @Autowired
    HelperFunctions helperFunctions;

    public RestException() {
        super();
    }

    @ExceptionHandler(MissingException.class)
    public ResponseEntity<?> handleMissingException(MissingException rnfe) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(new Date());
        errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetails.setTitle("Resource Not Found");
        errorDetails.setDetails(rnfe.getMessage());
        errorDetails.setDeveloperMessage(rnfe.getClass().getName());
        errorDetails.setErrors(helperFunctions.getConstraintViolations(rnfe));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(new Date());
        errorDetails.setStatus(status.value());
        errorDetails.setTitle("Rest Internal Exception");
        errorDetails.setDetails(ex.getMessage());
        errorDetails.setDeveloperMessage(ex.getClass().getName());
        errorDetails.setErrors(helperFunctions.getConstraintViolations(ex));

        return new ResponseEntity<>(errorDetails, status);
    }
}
