package com.lambdaschool.schools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The entity allowing interaction with the studcourses table.
 * The join table between course and students
 */
@Entity
@Table(name = "studcourses")
@IdClass(StudCoursesId.class)
public class StudCourses
    extends Auditable
    implements Serializable
{
    /**
     * Foreign key into the course table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "courseid")
    @JsonIgnoreProperties("students")
    private Course course;

    /**
     * Foreign key into the student table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "studentid")
    @JsonIgnoreProperties("courses")
    private Student student;

    public StudCourses()
    {
    }

    /**
     * Given the params, create a new course student combination
     *
     * @param course  The course object of the course student combination
     * @param student The student object of the course student combination
     */
    public StudCourses(
        Course course,
        Student student)
    {
        this.course = course;
        this.student = student;
    }

    /**
     * The getter for course
     *
     * @return the complete course object of this course student combination
     */
    public Course getCourse()
    {
        return course;
    }

    /**
     * The setter for course
     *
     * @param course change the course object associated with this course student combination to this one.
     */
    public void setCourse(Course course)
    {
        this.course = course;
    }

    /**
     * The getter for student
     *
     * @return the complete student object of this course student combination
     */
    public Student getStudent()
    {
        return student;
    }

    /**
     * The setter for student
     *
     * @param student change the student object associated with this course student combination to this one.
     */
    public void setStudent(Student student)
    {
        this.student = student;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        StudCourses that = (StudCourses) o;
        return ((this.student == null) ? 0 : this.student.getStudentid()) == ((that.student == null) ? 0 : that.student.getStudentid()) &&
            ((this.course == null) ? 0 : this.course.getCourseid()) == ((that.course == null) ? 0 : that.course.getCourseid());
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
