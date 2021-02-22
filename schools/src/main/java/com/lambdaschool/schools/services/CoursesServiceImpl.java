package com.lambdaschool.schools.services;

import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.Course;
import com.lambdaschool.schools.models.Instructor;
import com.lambdaschool.schools.models.StudCourses;
import com.lambdaschool.schools.models.Student;
import com.lambdaschool.schools.repositories.CourseRepository;
import com.lambdaschool.schools.repositories.InstructorRepository;
import com.lambdaschool.schools.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the CoursesService
 */
@Service(value = "coursesService")
public class CoursesServiceImpl
    implements CoursesService
{
    /**
     * Connects this service to the Course table.
     */
    @Autowired
    private CourseRepository courserepos;

    /**
     * Connects this service to the Student table.
     */
    @Autowired
    private StudentRepository studentrepos;

    /**
     * Connects this service to the Instructor table.
     */
    @Autowired
    private InstructorRepository instructorrepos;

    @Override
    public List<Course> findAll()
    {
        List<Course> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        courserepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Course findCourseById(long id)
    {
        return courserepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Course id " + id + " not found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        courserepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Course id " + id + " not found!"));
        courserepos.deleteById(id);
    }

    @Transactional
    @Override
    public Course save(Course course)
    {
        Course newCourse = new Course();

        if (course.getCourseid() != 0)
        {
            Course oldCourse = courserepos.findById(course.getCourseid())
                .orElseThrow(() -> new ResourceNotFoundException("Course id " + course.getCourseid() + " not found!"));

            newCourse.setCourseid(course.getCourseid());
        }

        newCourse.setCoursename(course.getCoursename());
        Instructor newInstructor = instructorrepos.findById(course.getInstructor()
            .getInstructorid())
            .orElseThrow(() -> new ResourceNotFoundException("Instructor id " + course.getInstructor()
                .getInstructorid() + " not found!"));
        newCourse.setInstructor(newInstructor);

        newCourse.getStudents()
            .clear();
        for (StudCourses sc : course.getStudents())
        {
            Student newStudent = studentrepos.findById(sc.getStudent()
                .getStudentid())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor id " + sc.getStudent()
                    .getStudentid() + " not found!"));

            newCourse.getStudents()
                .add(new StudCourses(newCourse,
                    newStudent));
        }

        return courserepos.save(newCourse);
    }
}
