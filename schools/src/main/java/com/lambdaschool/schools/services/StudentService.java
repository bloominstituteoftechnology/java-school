package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.Student;

import java.util.List;

/**
 * The service that works with the Student Model
 */
public interface StudentService
{
    /**
     * Returns a list of all the Students
     *
     * @return List of Students. If no students, empty list.
     */
    List<Student> findAll();

    /**
     * Returns the student with the given primary key.
     *
     * @param id The primary key (long) of the student you seek.
     * @return The given student or throws an exception if not found.
     */
    Student findStudentById(long id);

    /**
     * Deletes the student record and its student and course combinations from the database based off of the provided primary key
     *
     * @param id The primary key (long) of the student you seek.
     */
    void delete(long id);

    /**
     * Given a complete student object, saves that student object in the database.
     * If a primary key is provided, the record is completely replaced
     * If no primary key is provided, one is automatically generated and the record is added to the database.
     *
     * @param student the student object to be saved
     * @return the saved student object including any automatically generated fields
     */
    Student save(Student student);
}
