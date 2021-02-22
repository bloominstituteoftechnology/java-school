package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.ValidationError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Service(value="helperFunctions")
public class HelperFuntionsImpl implements HelperFunctions{
    @Override
    public List<ValidationError> getConstraintViolation(Throwable cause) {
        while((cause != null) && !(cause instanceof org.hibernate.exception.ConstraintViolationException || cause instanceof MethodArgumentNotValidException)){
            cause = cause.getCause();
        }
        List<ValidationError> validationerrors = new ArrayList<>();
        if(cause != null){
            if(cause instanceof org.hibernate.exception.ConstraintViolationException){
                org.hibernate.exception.ConstraintViolationException ve = (ConstraintViolationException) cause;
                ValidationError newve = new ValidationError();
                newve.setCode(ve.getMessage());
                newve.setMessage(ve.getConstraintName());
                validationerrors.add(newve);
            }else{
                MethodArgumentNotValidException ma = (MethodArgumentNotValidException) cause;
                List<FieldError> fielderrors = ma.getBindingResult().getFieldErrors();
                for(FieldError f:fielderrors){
                    ValidationError newve = new ValidationError();
                    newve.setCode(f.getField());
                    newve.setMessage(f.getDefaultMessage());
                    validationerrors.add(newve);
                }
            }
        }

        return validationerrors;
    }
}
