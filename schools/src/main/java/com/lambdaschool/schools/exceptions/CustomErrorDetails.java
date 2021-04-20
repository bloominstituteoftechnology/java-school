package com.lambdaschool.schools.exceptions;

import com.lambdaschool.schools.services.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CustomErrorDetails extends DefaultErrorAttributes {

  @Autowired
  private HelperFunctions helperFunctions;

  /*
  title
  status
  detail
  timestamp
  developerMessage
   */

  SimpleDateFormat dateFormat = new SimpleDateFormat("E, MMM dd,yyyy | HH:mm:ss");
  Date date = new Date();
  @Override
  public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace){
    Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

    Map<String, Object> rtnAttributes = new LinkedHashMap<>();
    rtnAttributes.put("title", errorAttributes.get("error"));
    rtnAttributes.put("status", errorAttributes.get("status"));
    rtnAttributes.put("details", errorAttributes.get("message"));
    rtnAttributes.put("timestamp", dateFormat.format(date));
    rtnAttributes.put("developerMessage", "path: " + errorAttributes.get("path"));
    rtnAttributes.put("errors", helperFunctions.getValidationErrors(this.getError(webRequest)));

    return rtnAttributes;
  }
}
