package com.lambdaschool.schools.services;

import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.Instructor;
import com.lambdaschool.schools.models.StudCourses;
import com.lambdaschool.schools.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class InstuctorServiceImpl implements InstructorService{

  @Autowired
  private InstructorRepository instructorrepos;

  @Override
  public List<Instructor> findAll() {
    List<Instructor> list = new ArrayList<>();
    instructorrepos.findAll()
                   .iterator()
                   .forEachRemaining(list::add);
    return list;
  }

  @Override
  public Instructor findInstructorById(long id) {

    return instructorrepos.findById(id)
        .orElseThrow(()->new ResourceNotFoundException(
            "Instructor id " + id + " not found!"));
  }

  @Override
  public void delete(long id) {
    instructorrepos.findById(id)
                   .orElseThrow(()->new ResourceNotFoundException(
                       "Instructor id " + id + " not found!"));
    instructorrepos.deleteById(id);
  }

  @Override
  public Instructor save(Instructor instructor) {
    Instructor newInstructor = new Instructor();

    if (instructor.getInstructorid() != 0){
      Instructor oldInstructor = instructorrepos.findById(
          instructor.getInstructorid()).orElseThrow(()->new
          ResourceNotFoundException("Instructor id " + instructor.getInstructorid()
                                        + " not found!"));

      newInstructor.setInstructorid(instructor.getInstructorid());
    }

    newInstructor.setName(instructor.getName());
    newInstructor.setCourses(instructor.getCourses());
    return instructorrepos.save(newInstructor);
  }

  @Override
  public Instructor addAdvice(Instructor instructor, long id) {
    Instructor newAdvice = new Instructor();
    instructorrepos.findById(id)
                   .orElseThrow(()->new ResourceNotFoundException(
                       "Instructor id " + id + " not found!"));

    newAdvice.setInstructorid(instructor.getInstructorid());
    return instructorrepos.save(newAdvice);
  }
}
