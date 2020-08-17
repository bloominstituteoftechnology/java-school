package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.ValidationError;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions
{
    // Find any data violations that might be associated with the error and report them
    // data validations get wrapped in other exceptions as we work through the Spring
    // exception chain. Hence we have to search the entire Spring Exception Stack
    // to see if we have any violation constraints.
    @Override
    public List<ValidationError> getConstraintViolation(Throwable cause)
    {
        // loop through all exceptions out there until I run out or ConstraintViolationException
        while ((cause != null) && !(cause instanceof ConstraintViolationException))
        {
            cause = cause.getCause();
        };

        List<ValidationError> listVE = new ArrayList<>();

        if (cause != null) // found validation exception, do this:
        {
            ConstraintViolationException ex = (ConstraintViolationException) cause; // typecast cause
            for (ConstraintViolation cv : ex.getConstraintViolations())
            {
                ValidationError newVe = new ValidationError();

                newVe.setCode(cv.getInvalidValue().toString());
                newVe.setMessage(cv.getMessage());
                listVE.add(newVe);
            }
        }
        return listVE;
    }
}
