package com.lambdaschool.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc

@SpringBootApplication
public class SchoolApplication
{

    public static void main(String[] args)
    {
        ApplicationContext ctx = SpringApplication.run(SchoolApplication.class, args);

        //servlet is a piece of code running on backend that handles web communications for us
        //provides a service
        ///only time we will interact with dispatcherservlet
        DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
        ///wanting to find the dispatcherServlet bean
        //got to typecast to a dispatcherServlet
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }

}