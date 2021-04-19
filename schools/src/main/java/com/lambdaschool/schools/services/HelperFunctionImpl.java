package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.ValidationError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunction")
public class HelperFunctionImpl implements HelperFunction{


    @Override
    public List<ValidationError> getValiErrors(Throwable cause) {
        List<ValidationError> validationErrors = new ArrayList<>();

        while (cause != null && !(cause instanceof ConstraintViolationException || cause instanceof MethodArgumentNotValidException)){
            cause = cause.getCause();

            if(cause != null){
                if(cause instanceof ConstraintViolationException){
                    ConstraintViolationException newException = (ConstraintViolationException)cause;

                    ValidationError newValidationError = new ValidationError();
                    newValidationError.setMessage(newException.getConstraintName());
                    newValidationError.setFieldname(newException.getMessage());

                    validationErrors.add(newValidationError);
                } else{MethodArgumentNotValidException newException = (MethodArgumentNotValidException) cause;

                    List<FieldError> fieldErrors = newException.getBindingResult().getFieldErrors();
                    for(FieldError f : fieldErrors){
                        ValidationError newValidationError = new ValidationError();
                        newValidationError.setFieldname(f.getField());
                        newValidationError.setMessage(f.getDefaultMessage());

                        validationErrors.add(newValidationError);
                    }
                }
            }
        }
        return validationErrors;
    }
}
