package com.lambdaschool.schools.services;


import com.lambdaschool.schools.models.ValidationError;

import java.util.List;

public interface HelperFunction {

    List<ValidationError> getValiErrors(Throwable cause);
}
