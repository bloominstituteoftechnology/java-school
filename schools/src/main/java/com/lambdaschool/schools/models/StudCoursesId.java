package com.lambdaschool.schools.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StudCoursesId implements Serializable
{
    /**
     * The id of the student
     */
    private long student;

    /**
     * The id of the course
     */
    private long course;

    /**
     * The default constructor
     */
    public StudCoursesId()
    {
    }

    public long getStudent()
    {
        return student;
    }

    public void setStudent(long student)
    {
        this.student = student;
    }

    public long getCourse()
    {
        return course;
    }

    public void setCourse(long course)
    {
        this.course = course;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        // boolean temp = (o.getClass() instanceof Class);
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        StudCoursesId that = (StudCoursesId) o;
        return student == that.student &&
            course == that.course;
    }

    @Override
    public int hashCode()
    {
        return 37;
    }

}
