package com.lambdaschool.schools.controllers;

import com.lambdaschool.schools.models.Instructor;
import com.lambdaschool.schools.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "instructors")
public class InstructorController
{
    @Autowired
    private InstructorService instructorService;

    // http://localhost:2019/instructors/instructor/3/advice
    @GetMapping(value = "/instructor/{instructorid}/advice", produces = "application/json")
    public ResponseEntity<?> getInstructorAdvice(
        @PathVariable Long instructorid)
    {
        Instructor i = instructorService.addAdvice(instructorid);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

}
