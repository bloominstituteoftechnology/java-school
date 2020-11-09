package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.ValidationError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunctions")
public class HelpFunctionsImpl implements HelperFunctions {
    @Override
    public List<ValidationError> getConstraintViolations(Throwable cause) {
        List<ValidationError> listVE = new ArrayList<>();

        while((cause != null) &&
            !(cause instanceof ConstraintViolationException) &&
            !(cause instanceof MethodArgumentNotValidException)) {
            cause = cause.getCause();
        }

        if (cause != null) {
            if (cause instanceof ConstraintViolationException) {
                ConstraintViolationException ex = (ConstraintViolationException) cause;

                ValidationError newVE = new ValidationError();
                newVE.setFieldname(ex.getConstraintName());
                newVE.setMessage(ex.getMessage());

                listVE.add(newVE);
            } else {
                MethodArgumentNotValidException ex = (MethodArgumentNotValidException) cause;

                List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
                for (FieldError fe : fieldErrors) {
                    ValidationError newVE = new ValidationError();
                    newVE.setFieldname(fe.getField());
                    newVE.setMessage(fe.getDefaultMessage());

                    listVE.add(newVE);
                }
            }
        }

        return listVE;
    }
}
