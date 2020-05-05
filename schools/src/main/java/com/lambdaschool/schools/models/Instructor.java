package com.lambdaschool.schools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * The entity allowing interaction with the instructors table
 */
@Entity
@Table(name = "instructors")
public class Instructor
    extends Auditable
{
    /**
     * The primary key (long) of the instructor table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long instructorid;

    /**
     * The Instructor's name (String)
     */
    @Column(nullable = false)
    private String name;

    /**
     * List of courses associated with this instructor. Does not get saved in the database directly.
     * Forms a one to many relationship with courses. One instructor to many courses.
     */
    @OneToMany(mappedBy = "instructor",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties("instructor")
    private List<Course> courses = new ArrayList<>();

    /**
     * Default Constructor used primarily by the JPA.
     */
    public Instructor()
    {
    }

    /**
     * Given the name of instructor, add them
     *
     * @param name The name (String) for the new instructor
     */
    public Instructor(
        String name)
    {
        this.name = name;
    }

    /**
     * Getter for the instructor id
     *
     * @return The primary key (long) for this instructor
     */
    public long getInstructorid()
    {
        return instructorid;
    }

    /**
     * Setter for the instructor id
     *
     * @param instructorid The new primary key (long) for this instructor
     */
    public void setInstructorid(long instructorid)
    {
        this.instructorid = instructorid;
    }

    /**
     * the instructor name
     *
     * @return the instructor name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setter for the instructor name
     *
     * @param name the new instructor name (String) for this instructor
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Getter for courses
     *
     * @return The list of courses this instructor is teaching
     */
    public List<Course> getCourses()
    {
        return courses;
    }

    /**
     * Setter for courses
     *
     * @param courses The new list of courses this instructor is teaching
     */
    public void setCourses(List<Course> courses)
    {
        this.courses = courses;
    }
}
