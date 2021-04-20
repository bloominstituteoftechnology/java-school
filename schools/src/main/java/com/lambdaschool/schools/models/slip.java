package com.lambdaschool.schools.models;

import com.sun.istack.NotNull;

public class slip {
    private long id;
    @NotNull
    private String advice;


    public slip() {
    }




    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    @Override
    public String toString() {
        return "slip{" +
                "id=" + id +
                ", advice='" + advice + '\'' +
                '}';
    }
}
