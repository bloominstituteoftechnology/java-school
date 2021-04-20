package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.Instructor;

import java.util.List;

public interface InstructorService {
  List<Instructor> findAll();

  Instructor findInstructorById(long id);

  void delete(long id);

  Instructor save(Instructor instructor);

 Instructor addAdvice(Instructor instructor, long id);
}
