package com.lambdaschool.schools.exceptions;

import com.lambdaschool.schools.services.HelperFunctions;
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
    private HelperFunctions helperFunctions;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        Map<String, Object> rtnAttributes = new LinkedHashMap<>();

        rtnAttributes.put("title", errorAttributes.get("error"));
        rtnAttributes.put("status", errorAttributes.get("status"));
        rtnAttributes.put("detail", errorAttributes.get("message"));
        rtnAttributes.put("timestamp", new Date());
        rtnAttributes.put("developerMessage", "path: " + errorAttributes.get("path"));

        rtnAttributes.put("errors", helperFunctions.getValidationErrors(this.getError(webRequest)));

        return rtnAttributes;
    }
}
