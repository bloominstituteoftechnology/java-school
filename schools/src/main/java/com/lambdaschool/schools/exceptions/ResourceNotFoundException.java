package com.lambdaschool.schools.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super("Found an issue with School:" + message);
    }

}
