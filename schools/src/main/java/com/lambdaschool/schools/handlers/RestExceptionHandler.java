package com.lambdaschool.schools.handlers;

//import org.springframework.web.servlet.NoHandlerFoundException;
import com.lambdaschool.schools.exceptions.NoHandlerFoundException;
import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.ErrorDetail;
import com.lambdaschool.schools.services.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is the driving class when an exception occurs. All exceptions are handled here.
 * This class is shared across all controllers due to the annotation RestControllerAdvice;
 * this class gives advice to all controllers on how to handle exceptions.
 * Due to the annotation Order(Ordered.HIGHEST_PRECEDENCE), this class takes precedence over all other controller advisors.
 */
// Created a BEAN, and override some of Springs methods
@Order(Ordered.HIGHEST_PRECEDENCE) // if we run into multi rest controllers and have an exception, come over here bc the others won't make sense
@RestControllerAdvice // when a controller runs into a prob, spring looks into advices
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
    @Autowired
    private HelperFunctions helperFunctions;

    /**
     * The constructor for the RestExceptionHandler. Currently we do not do anything special. We just call the parent constructor.
     */
    public RestExceptionHandler()
    {
        super();
    }

    @ExceptionHandler(ResourceNotFoundException.class) // handle this exception this way:
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe) // capture in method and do something with it
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Date());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDeveloperMessage(rnfe.getClass().getName());

        errorDetail.setErrors(helperFunctions.getConstraintViolation(rnfe));
        // getConstraintViolation() comes from User model > validation constraint @Email (checked if it's properly formatted) -- @password is another
        // !!!!! list of java bean validations: https://www.baeldung.com/javax-validation

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
//    public ResponseEntity<?> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException rfe)
//    {
//        ErrorDetail errorDetail = new ErrorDetail();
//        errorDetail.setTimestamp(new Date());
//        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
//        errorDetail.setTitle("Rest Internal Exception");
//        errorDetail.setDetail(rfe.getMessage());
//        errorDetail.setDeveloperMessage(rfe.getClass()
//            .getName());
//        errorDetail.setErrors(helperFunctions.getConstraintViolation(rfe));
//
//        return new ResponseEntity<>(errorDetail,
//            null,
//            HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(NoHandlerFoundException.class) // handle this exception this way:
//    public ResponseEntity<?> handleResourceNotFoundException(NoHandlerFoundException nhfe) // capture in method and do something with it
//    {
//        ErrorDetail errorDetail = new ErrorDetail();
//        errorDetail.setTimestamp(new Date());
//        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
//        errorDetail.setTitle("Rest Internal Exception");
//        errorDetail.setDetail(nhfe.getMessage());
//        errorDetail.setDeveloperMessage(nhfe.getClass().getName());
//
//        errorDetail.setErrors(helperFunctions.getConstraintViolation(nhfe));
//
//        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
//    }

    // if you don't have a rest exception handled anywhere in system, do this:
    // *this is minimum you should use if you create you're own but other examples in repos
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
        Exception ex,
        Object body,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request)
    {
        // response entity like everything else
        // this is more backwards compatible - <?> is not
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Date());
        errorDetail.setStatus(status.value()); // .value bc we want it in integer form
        errorDetail.setTitle("Rest Internal Exception"); // could do ex.getMessage or getCause, etc
        errorDetail.setDetail("Found an issue with School: " + ex.getMessage());
        errorDetail.setDeveloperMessage(ex.getClass().getName()); // not very helpful but need to send something back

        errorDetail.setErrors(helperFunctions.getConstraintViolation(ex)); // send it the exception and then it will loop through in helperfunction
        return new ResponseEntity<>(errorDetail, null, status); // usually 500 error but not if spring knows its a 500 error
    }
}
