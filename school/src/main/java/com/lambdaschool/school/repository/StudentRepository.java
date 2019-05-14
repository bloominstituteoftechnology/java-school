package com.lambdaschool.school.repository;

import com.lambdaschool.school.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long>
{
    List<Student> findByStudnameContainingIgnoreCase(String name);
}
