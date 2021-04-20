package com.lambdaschool.schools;

import com.lambdaschool.schools.models.slip;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public static void main(String[] args)
    {

        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));

        restTemplate.getMessageConverters().add(converter);

        String requestUrl = "https://api.adviceslip.com/advice";

        ParameterizedTypeReference<slip> responseType = new ParameterizedTypeReference<>() {
        };

        ResponseEntity<slip> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET,null,responseType);


        slip ourSlip = responseEntity.getBody();
        System.out.println(ourSlip);



        SpringApplication.run(SchoolsApplication.class,
            args);
    }

}
