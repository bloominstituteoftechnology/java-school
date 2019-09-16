package com.lambdaschool.school.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice///when a controller runs
///it will look at controlleradvice for advice on how to handle exceptions
//by extending ResponseEntityExceptionHandler, we know it will handle exceptions
///spring will recognize this and when response entities have exceptions it will look at controller advice to figure out
//what to do with them
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{

}