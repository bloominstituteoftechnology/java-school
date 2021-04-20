package com.lambdaschool.schools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The Entity allowing interaction with the students table
 */
@ApiModel(value = "Students,", description = "Student record")
@Entity
@Table(name = "students")
public class Student
    extends Auditable
{
    /**
     * The primary key (long) of the students table
     */
    @ApiModelProperty(name = "student id", value = "primary key of student", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long studentid;

    /**
     * The name student (String)
     */
    @ApiModelProperty(name = "Student name", value = "Students actual name", required = true, example = "Bucky Barnes")
    @Column(nullable = false,
        unique = true)
    private String name;

    /**
     * Part of the join relationship between student and course
     * connects students to the student course combination
     */
    @ApiModelProperty(name = "Student Classes", value = "name of the class", required = false, example = "Java Back End")
    @OneToMany(mappedBy = "student",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "student",
        allowSetters = true)
    private Set<StudCourses> courses = new HashSet<>();

    /**
     * Default constructor used primarily by the JPA.
     */
    public Student()
    {
    }

    /**
     * Getter for the Student Id
     *
     * @return the student id, primary key, (long) of this student
     */
    public long getStudentid()
    {
        return studentid;
    }

    /**
     * Setter for Student id
     *
     * @param studentid the new primary key (long) for this student
     */
    public void setStudentid(long studentid)
    {
        this.studentid = studentid;
    }

    /**
     * Getter for student name
     *
     * @return the name (String) of this student
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setter for student name
     *
     * @param name the new name (String) for this student
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Getter for courses associated with this student
     *
     * @return list of student courses combinations associated with this student
     */
    public Set<StudCourses> getCourses()
    {
        return courses;
    }

    /**
     * Setter for courses associated with this student
     *
     * @param courses the new list of student courses combinations associated with this student
     */
    public void setCourses(Set<StudCourses> courses)
    {
        this.courses = courses;
    }
}
