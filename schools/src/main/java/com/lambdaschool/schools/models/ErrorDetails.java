package com.lambdaschool.schools.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrorDetails {

    private String title;
    private int status;
    private String detail;
    private Date timestamp;
    private String developerMessage;
    private List<ValidationError> error = new ArrayList<>();

    public ErrorDetails() {
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

    public List<ValidationError> getError() {
        return error;
    }

    public void setError(List<ValidationError> error) {
        this.error = error;
    }
}
