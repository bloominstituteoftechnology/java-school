package com.lambdaschool.schools.exceptions;

import com.lambdaschool.schools.services.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

//this changes it from a pojo to a bean
@Component
public class CustomErrorDetails extends DefaultErrorAttributes {
    @Autowired
    HelperFunctions helperFunctions;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object > errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put("title", errorAttributes.get("error"));
        errorDetails.put("status", errorAttributes.get("status"));
        errorDetails.put("details", errorAttributes.get("message"));
        errorDetails.put("timestamp", errorAttributes.get("timestamp"));

        errorAttributes.put("developerMessage", "path " + errorAttributes.get("path"));

        errorAttributes.put("errors", helperFunctions.getConstraintViolation(this.getError(webRequest)));


        return errorDetails;
    }
}