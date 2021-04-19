package com.lambdaschool.schools.models;

public class ValidationError {
    private String fieldname;
    private String message;

    public ValidationError() {
    }

    public ValidationError(String fieldname, String message) {
        this.fieldname = fieldname;
        this.message = message;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


