package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.ValidationError;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions
{
    @Override
    public List<ValidationError> getValidationErrors(Throwable cause)
    {
        List<ValidationError> validationErrorList = new ArrayList<>();

        while (cause != null && !(cause instanceof ConstraintViolationException || cause instanceof MethodArgumentNotValidException))
        {
            cause = cause.getCause();
        }

        if (cause != null)
        {
            if (cause instanceof ConstraintViolationException)
            {
                ConstraintViolationException ex = (ConstraintViolationException)cause;

               ValidationError newVE = new ValidationError();
               newVE.setMessage();
            }
        }
    }
}
