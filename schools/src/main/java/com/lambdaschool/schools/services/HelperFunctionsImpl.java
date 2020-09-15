package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.ValidationError;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions{

    @Override
    public List<ValidationError> getConstraintViolations(Throwable cause) {

        while((cause != null) && !(cause instanceof ConstraintViolationException)){
            cause = cause.getCause();
        }
        List<ValidationError> listVe = new ArrayList<>();

        if(cause != null){
            ConstraintViolationException ex =
                    (ConstraintViolationException) cause;
            for(ConstraintViolation cv : ex.getConstraintViolations()){
                ValidationError newVe = new ValidationError();

                newVe.setCode((cv.getInvalidValue().toString()));
                newVe.setMessage(cv.getMessage());
                listVe.add(newVe);
            }
        }

        return listVe;
    }
}
