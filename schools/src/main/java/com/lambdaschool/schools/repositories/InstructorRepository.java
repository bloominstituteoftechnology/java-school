package com.lambdaschool.schools.repositories;

import com.lambdaschool.schools.models.Instructor;
import org.springframework.data.repository.CrudRepository;

/**
 * The CRUD repository connecting Instructors to the rest of the application
 */
public interface InstructorRepository
    extends CrudRepository<Instructor, Long>
{

}
