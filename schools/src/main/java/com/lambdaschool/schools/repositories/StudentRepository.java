package com.lambdaschool.schools.repositories;

import com.lambdaschool.schools.models.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * The CRUD repository connecting  to the rest of the application
 */
public interface StudentRepository
    extends CrudRepository<Student, Long>
{
}
