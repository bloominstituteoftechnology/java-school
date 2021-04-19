package com.lambdaschool.schools.exceptions;

import com.lambdaschool.schools.services.HelperFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomErrorDetails extends DefaultErrorAttributes {

    @Autowired
    private HelperFunction helperFunction;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errors = super.getErrorAttributes(webRequest, includeStackTrace);

        Map<String, Object> newFormat = new LinkedHashMap<>();

        newFormat.put("title", errors.get("error"));
        newFormat.put("status", errors.get("status"));
        newFormat.put("detail", errors.get("message"));
        newFormat.put("timestamp", new Date());
        newFormat.put("developerMessage","path " + errors.get("path"));

        newFormat.put("errors", helperFunction.getValiErrors(this.getError(webRequest)));

        return newFormat;

    }
}
