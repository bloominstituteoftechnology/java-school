package com.lambdaschool.schools.models;

public class ValidationError
{
    private String code; // which fields are not valid and..
    private String message; // why

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "ValidationError{" + "Code='" + code + '\'' + ", message='" + message + '\'' + '}';
    }
}
