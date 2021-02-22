package com.lambdaschool.schools.exceptions;

public class NoHandlerFoundException extends RuntimeException{
    public NoHandlerFoundException(String message){
        super("found and issue with school: "+ message);
    }

}
