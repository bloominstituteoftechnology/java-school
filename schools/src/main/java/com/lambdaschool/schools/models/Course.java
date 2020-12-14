package com.lambdaschool.schools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The entity allowing interaction with the courses table
 */
@ApiModel(value = "Course",
    description = "This is the course record")
@Entity
@Table(name = "courses")
@JsonIgnoreProperties(value = {"hasinstructorforcourse"})
public class Course extends Auditable
{
    @ApiModelProperty(name = "course id",
        value = "primary key for course",
        required = true,
        example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseid;

    @ApiModelProperty(name = "course name",
        value = "full course name",
        required = true,
        example = "Physics 101")
    @Column(nullable = true,
        unique = true)
    private String coursename;

    @Transient
    public boolean hasinstructorforcourse = false;

    @ApiModelProperty(name = "course name",
        value = "instructor of course",
        require = false,
        example = "")
    private String instructor;

    @ManyToOne
    @JoinColumn(name = "instructorid",
        nullable = false)
    @JsonIgnoreProperties(value = "courses",
        allowSetters = true)
    private Instructor instructor;

    /**
     * Part of the join relationship between course and students
     * connects course to a course student combination
     */
    @OneToMany(mappedBy = "course",
        cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "course",
        allowSetters = true)
    private List<StudCourses> students = new ArrayList<>();

    /**
     * Getter for primary key of this course
     *
     * @return the primary key (long) for this course
     */
    public long getCourseid()
    {
        return courseid;
    }

    /**
     * Setter for the primary key of this course
     *
     * @param courseid the new primary key (long) for this course
     */
    public void setCourseid(long courseid)
    {
        this.courseid = courseid;
    }

    /**
     * Getter for the name of this course
     *
     * @return The name (String) of this course
     */
    public String getCoursename()
    {
        return coursename;
    }

    /**
     * Setter for the name of this course
     *
     * @param coursename the new name (String) for this course
     */
    public void setCoursename(String coursename)
    {
        this.coursename = coursename;
    }

    /**
     * Getter for the course student combinations for this course
     *
     * @return A list of course student combinations for this course
     */
    public List<StudCourses> getStudents()
    {
        return students;
    }

    /**
     * Setter for the course student combinations for this course
     *
     * @param students A new list of course student combinations associated with course
     */
    public void setStudents(List<StudCourses> students)
    {
        this.students = students;
    }

    /**
     * Getter for the instructor assigned to this course
     *
     * @return the full instructor object assigned to this course.
     */
    public Instructor getInstructor()
    {
        return instructor;
    }

    /**
     * Setter for instructor
     *
     * @param instructor the new instructor for this course
     */
    public void setInstructor(Instructor instructor)
    {
        this.instructor = instructor;
    }

    /**
     * Add student to this course
     *
     * @param student the new student (Student) to add
     */
    public void addStudent(
        Student student)
    {
        students.add(new StudCourses(this,
            student));
    }
}
