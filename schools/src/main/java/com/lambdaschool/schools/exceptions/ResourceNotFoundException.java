package com.lambdaschool.schools.exceptions;

public class ResourceNotFoundException extends  RuntimeException{
    public ResourceNotFoundException(String message){
        super("Error from "+ message);
    }
}
