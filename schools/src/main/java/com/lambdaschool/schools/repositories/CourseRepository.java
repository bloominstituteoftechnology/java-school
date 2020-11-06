package com.lambdaschool.schools.repositories;

import com.lambdaschool.schools.models.Course;
import org.springframework.data.repository.CrudRepository;

/**
 * The CRUD repository connecting Courses to the rest of the application
 */
public interface CourseRepository
    extends CrudRepository<Course, Long>
{

}
