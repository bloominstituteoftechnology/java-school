package com.lambdaschool.schools.exceptions;

public class MissingException extends RuntimeException {
    public MissingException(String message) {
        super("Exception from SCHOOL APP:" + message);
    }
}

