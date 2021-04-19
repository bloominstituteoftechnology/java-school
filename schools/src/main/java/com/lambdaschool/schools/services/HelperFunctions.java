package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.ValidationError;

import java.util.List;

public interface HelperFunctions
{
    List<ValidationError> getValidationErrors(Throwable cause);
}
