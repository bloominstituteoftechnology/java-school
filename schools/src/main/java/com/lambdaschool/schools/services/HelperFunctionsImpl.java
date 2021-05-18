package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.ValidationError;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.hibernate.exception.ConstraintViolationException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions{

    @Override
    public List<ValidationError> getConstraintViolation(Throwable cause) {
        List<ValidationError> listVE = new ArrayList<>();

        while((cause != null) && !(cause instanceof ConstraintViolationException || cause instanceof MethodArgumentNotValidException))
        {

            cause = cause.getCause();

        }

        if (cause != null)
        {
            if(cause instanceof ConstraintViolationException)
            {
                ValidationError newVE = new ValidationError();
                ConstraintViolationException eX = (ConstraintViolationException) cause;
                newVE.setCode(eX.getMessage());
                newVE.setMessage(eX.getConstraintName());
                listVE.add(newVE);
            } else
            {

                MethodArgumentNotValidException ex = (MethodArgumentNotValidException) cause;
                List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

                for(FieldError fe : fieldErrors)
                {

                    ValidationError newVE = new ValidationError();
                    newVE.setCode(fe.getField());
                    newVE.setMessage(fe.getDefaultMessage());
                    listVE.add(newVE);

                }

            }
        }

        return listVE;
    }
}
