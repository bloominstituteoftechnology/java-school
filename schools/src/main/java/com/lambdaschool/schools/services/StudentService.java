package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.Student;

import java.util.List;

public interface StudentService
{
    List<Student> findAll();

    Student findStudentById(long id);

    void delete(long id);

    Student save(Student student);
}
