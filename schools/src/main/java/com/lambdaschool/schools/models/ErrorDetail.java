package com.lambdaschool.schools.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrorDetail {

    private String title;

    private int status;

    private String detail;

    private Date timestamp;

    private String developerMessage;

    private List<ValidationError> errors = new ArrayList<>();

    public ErrorDetail() {
    }

    public ErrorDetail(String title, int status, String detail, Date timestamp, String developerMessage, List<ValidationError> errors) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.timestamp = timestamp;
        this.developerMessage = developerMessage;
        this.errors = errors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }
}