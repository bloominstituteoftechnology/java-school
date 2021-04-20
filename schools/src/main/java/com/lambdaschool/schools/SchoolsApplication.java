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
        /*
         * Creates the object that is needed to do a client side Rest API call.
         * We are the client getting data from a remote API.
         */
        RestTemplate restTemplate = new RestTemplate();

        // we need to tell our RestTemplate what format to expect
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // a couple of common formats
        // converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
        // converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        // or we can accept all formats! Easiest but least secure
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemplate.getMessageConverters().add(converter);

        String requestURL = "https://api.adviceslip.com/advice";
         ParameterizedTypeReference<slip> responseType = new ParameterizedTypeReference<>()
        {
        };

        // create the response entity. do the get and get back information
        ResponseEntity<slip> responseEntity = restTemplate.exchange(requestURL,
                                                                    HttpMethod.GET,
                                                                    null,
                                                                    responseType);

        try{
            System.out.println(System.getenv("PATH").toUpperCase());
        } catch (Exception e){
            System.out.println("No Such Environment Variable");
        } finally{
            // now that we have our data, let's print it to the console!
            slip ourSlip = responseEntity.getBody();
            System.out.println(ourSlip);
        SpringApplication.run(SchoolsApplication.class,
            args);
        }
    }
}
