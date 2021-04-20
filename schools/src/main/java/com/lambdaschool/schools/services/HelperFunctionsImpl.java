package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.ValidationError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions {
    @Override
    public List<ValidationError> getValidationErrors(Throwable cause) {
        List<ValidationError> validationErrorList = new ArrayList<>();
        // Find any data violations that might be associated with the error and report them
        // data validations get wrapped in other exceptions as we work through the Spring
        // exception chain. Hence we have to search the entire Spring Exception Stack
        // to see if we have any violation constraints.

        //JSON <-> Jackson <-> Java Objects <-> JPA/Hibernate <-> SQL
        while (cause != null && !(cause instanceof ConstraintViolationException || cause instanceof MethodArgumentNotValidException))

        {
            cause = cause.getCause();

        }
        if (cause != null) {
            if (cause instanceof ConstraintViolationException) {
                ConstraintViolationException ex = (ConstraintViolationException) cause;
                ValidationError newVE = new ValidationError();
                newVE.setMessage(ex.getConstraintName());
                newVE.setFieldname(ex.getMessage());
                validationErrorList.add(newVE);
            } else {
                MethodArgumentNotValidException ex = (MethodArgumentNotValidException) cause;

                List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

                for (FieldError fe : fieldErrors) {
                    ValidationError newVE = new ValidationError();
                    newVE.setMessage(fe.getDefaultMessage());
                    newVE.setFieldname(fe.getField());

                    validationErrorList.add(newVE);
                }
            }
        }
        return validationErrorList;
    }

}