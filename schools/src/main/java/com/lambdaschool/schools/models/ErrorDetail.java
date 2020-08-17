package com.lambdaschool.schools.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrorDetail
{
    private String title;
    private int status;
    private String detail; // user would read
    private Date timestamp;
    private String developerMessage; // internals - not giving away too much for security reasons
    // can use camelCase bc it's a POJO

    // Client sends JSON obj with some invalid fields, send list of all invalid fields:
    private List<ValidationError> errors = new ArrayList<>();
    // Now create ValidationError model
}
