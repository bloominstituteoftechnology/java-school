package com.lambdaschool.schools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class slip
{
    private long id;
    private String advice;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getAdvice()
    {
        return advice;
    }

    public void setAdvice(String advice)
    {
        this.advice = advice;
    }

    public String toString()
    {
        return "{slip: {" + "id:" + id + ", advice: " + advice + "}}";
    }
}
