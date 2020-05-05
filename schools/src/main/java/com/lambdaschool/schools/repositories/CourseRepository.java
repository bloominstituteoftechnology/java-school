package com.lambdaschool.schools.repositories;

import com.lambdaschool.schools.models.Course;
import com.lambdaschool.schools.views.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * The CRUD repository connecting Courses to the rest of the application
 */
public interface CourseRepository
    extends CrudRepository<Course, Long>
{
    /**
     * Counts the number of student course combinations for the given studentid and courseid. Answer should be only 0 or 1.
     *
     * @param studentid The student id of the student of this student course combination
     * @param courseid The course id of the course of this student course combination
     * @return A single number, a count
     */
    @Query(value = "SELECT COUNT(*) as count FROM studcourses WHERE studentid = :studentid AND courseid = :courseid",
        nativeQuery = true)
    JustTheCount checkStudentCourseCombo(
        long studentid,
        long courseid);

    /**
     * Deletes the given student, course combination
     *
     * @param studentid The student id of the student of this student course combination
     * @param courseid The course id of the course of this student course combination
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM studcourses WHERE studentid = :studentid AND courseid = :courseid", nativeQuery = true)
    void deleteStudentCourse(
        long studentid,
        long courseid);

    /**
     * Inserts the new student course combination
     *
     * @param uname  The username (String) of the user adding the record
     * @param studentid The student id of the student of this student course combination
     * @param courseid The course id of the course of this student course combination
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO studcourses(studentid, courseid, created_by, created_date, last_modified_by, last_modified_date) VALUES (:studentid, :courseid, :uname, CURRENT_TIMESTAMP, :uname, CURRENT_TIMESTAMP)",
        nativeQuery = true)
    void insertStudCourses(
        String uname,
        long studentid,
        long courseid);
}
