package com.lambdaschool.schools.exceptions;
// tells java its my own exception bc I extended RuntimeException
public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException(String message)
    {
        super("Found an error with School: " + message);
    }
}
