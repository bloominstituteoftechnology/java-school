package com.lambdaschool.schools.handlers;

import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.ErrorDetail;
import com.lambdaschool.schools.services.HelperFunction;
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
    private HelperFunction helperFunction;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe){
        ErrorDetail errorDetail = new ErrorDetail();

        errorDetail.getTimestamp(new Date());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDeveloperMessage(rnfe.getClass().getName());
        errorDetail.setErrors(helperFunction.getValiErrors(rnfe));

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();

        errorDetail.getTimestamp(new Date());
        errorDetail.setTitle("Rest Internal Exception");
        errorDetail.setStatus(status.value());
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setDeveloperMessage(ex.getClass().getName());
        errorDetail.setErrors(helperFunction.getValiErrors(ex));

        return new ResponseEntity<>(errorDetail, null, status );
    }
}
