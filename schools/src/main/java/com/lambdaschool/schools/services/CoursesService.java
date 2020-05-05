package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.Course;

import java.util.List;

/**
 * The Service that works with the Course Model
 */
public interface CoursesService
{
    /**
     * Returns a list of all the Courses
     *
     * @return List of Courses. If no courses, empty list.
     */
    List<Course> findAll();

    /**
     * Returns the course with the given primary key.
     *
     * @param id The primary key (long) of the course you seek.
     * @return The given course or throws an exception if not found.
     */
    Course findCourseById(long id);

    /**
     * Deletes the course record and its student and course combinations from the database based off of the provided primary key
     *
     * @param id The primary key (long) of the course you seek.
     */
    void delete(long id);

    /**
     * Given a complete course object, saves that course object in the database.
     * If a primary key is provided, the record is completely replaced
     * If no primary key is provided, one is automatically generated and the record is added to the database.
     *
     * @param course the course object to be saved
     * @return the saved course object including any automatically generated fields
     */
    Course save(Course course);

    /**
     * Delete a student's enrollment in a course
     *
     * @param studentid the student id of the student to unenroll
     * @param courseid the course id of the course the student is unenrolling
     */
    void deleteStudentCourse(
        long studentid,
        long courseid);

    /**
     * Enroll a student in a course
     *
     * @param studentid the student id of the student to enroll
     * @param courseid the course id of the course where the student wishes to enroll
     */
    public void addStudCourses(
        long studentid,
        long courseid);
}
