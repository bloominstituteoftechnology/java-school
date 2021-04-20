package com.lambdaschool.schools.exceptions;

public class ResourceNotFoundExeption extends RuntimeException
{
    public ResourceNotFoundExeption(String message)
    {
        super("Found an issue with School: " + message);
    }
}
