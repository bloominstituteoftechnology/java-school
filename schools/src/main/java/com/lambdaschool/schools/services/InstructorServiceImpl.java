package com.lambdaschool.schools.services;

import com.lambdaschool.schools.exceptions.ResourceNotFoundExeption;
import com.lambdaschool.schools.models.Instructor;
import com.lambdaschool.schools.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "instructorService")
public class InstructorServiceImpl implements InstructorService
{
    @Autowired
    private InstructorRepository instructorRepository;


    @Override
    public Instructor addAdvice(long id)
    {
        return instructorRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundExeption("Instructor " + id + " Not Found!"));
    }
}
