package com.lambdaschool.schools.exceptions;

// tell Springboot to use my layout from ErrorDetail model

import com.lambdaschool.schools.services.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@Component // BEAN
public class CustomErrorDetails extends DefaultErrorAttributes
{
    @Autowired
    HelperFunctions helperFunctions;

    @Override
    public Map<String, Object> getErrorAttributes(
        WebRequest webRequest,
        boolean includeStackTrace)
    {
        // grab all attributes spring knows about and include in my format
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        // linked hashmap - will keep elements in order
        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put("title", errorAttributes.get("error"));
        errorDetails.put("status", errorAttributes.get("status"));
        errorDetails.put("detail", errorAttributes.get("message"));
        errorDetails.put("timestamp", errorAttributes.get("timestamp"));
        errorDetails.put("developerMessage", "path " + errorAttributes.get("path"));

        // pull out all errors related to constraint/data violation
        errorDetails.put("errors", helperFunctions.getConstraintViolation(this.getError(webRequest)));

        return errorDetails;
    }
}
