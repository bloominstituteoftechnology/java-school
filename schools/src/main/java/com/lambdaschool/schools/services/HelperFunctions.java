package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.ValidationError;

import java.util.List;

public interface HelperFunctions
{
    // loop through everything that is a constraint violation and add to list
    List<ValidationError> getConstraintViolation(Throwable cause);
}
