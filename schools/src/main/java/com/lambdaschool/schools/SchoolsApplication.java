package com.lambdaschool.schools;

import com.lambdaschool.schools.models.slip;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Main class to start the application.
 */
@EnableJpaAuditing
@SpringBootApplication
public class SchoolsApplication
{

    /**
     * Main method to start the application.
     *
     * @param args Not used in this application.
     */
    public static void main(String[] args){

        try{
            System.out.println(System.getenv("PATH").toUpperCase());
        } catch (Exception e){
            System.out.println("No Such Environment Variable");
        } finally{
        SpringApplication.run(SchoolsApplication.class,
            args);
        }
    }
}
