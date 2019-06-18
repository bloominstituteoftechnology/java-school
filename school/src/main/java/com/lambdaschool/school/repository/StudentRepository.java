package com.lambdaschool.school.repository;

import com.lambdaschool.school.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;
import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long>
{
    List<Student> findByStudnameContainingIgnoreCase(String name);
}
