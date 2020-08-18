package com.lambdaschool.schools.exceptions;

public class NoHandlerFoundException extends RuntimeException
{
    public NoHandlerFoundException(String message)
    {
        super("Found an issue with School: " + message);
    }
}
