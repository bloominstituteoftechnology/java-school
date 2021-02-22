package com.lambdaschool.schools.exceptions;

import com.lambdaschool.schools.services.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomErrorDetails extends DefaultErrorAttributes {
    @Autowired
    HelperFunctions helperFunctions;
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> initialAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String, Object> customDetail = new LinkedHashMap<>();

        customDetail.put("title", initialAttributes.get("error"));
        customDetail.put("status", initialAttributes.get("status"));
        customDetail.put("detail", "Found an issue with School: "+initialAttributes.get("message"));
        customDetail.put("timestamp", initialAttributes.get("timestamp"));
        customDetail.put("developerMessage", initialAttributes.get("path"));


        customDetail.put("errors", helperFunctions.getConstraintViolation(this.getError(webRequest)));
        return customDetail;
    }
}
