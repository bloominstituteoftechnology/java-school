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

import javax.persistence.EntityExistsException;
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

            // delete the students for the old course we are replacing
            for (StudCourses ur : oldCourse.getStudents())
            {
                deleteStudentCourse(ur.getStudent()
                        .getStudentid(),
                    ur.getCourse()
                        .getCourseid());
            }
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
        if (course.getCourseid() == 0)
        {
            for (StudCourses sc : course.getStudents())
            {
                Student newStudent = studentrepos.findById(sc.getStudent()
                    .getStudentid())
                    .orElseThrow(() -> new ResourceNotFoundException("Instructor id " + sc.getStudent()
                        .getStudentid() + " not found!"));

                newCourse.addStudent(newStudent);
            }
        } else
        {
            // add the new students for the course we are replacing
            for (StudCourses sc : course.getStudents())
            {
                addStudCourses(sc.getStudent()
                    .getStudentid(), newCourse.getCourseid());
            }
        }

        return courserepos.save(newCourse);
    }

    @Transactional
    @Override
    public void deleteStudentCourse(
        long studentid,
        long courseid)
    {
        studentrepos.findById(studentid)
            .orElseThrow(() -> new ResourceNotFoundException("Student id " + studentid + " not found!"));
        courserepos.findById(courseid)
            .orElseThrow(() -> new ResourceNotFoundException("Course id " + courseid + " not found!"));

        if (courserepos.checkStudentCourseCombo(studentid,
            courseid)
            .getCount() > 0)
        {
            courserepos.deleteStudentCourse(studentid,
                courseid);
        } else
        {
            throw new ResourceNotFoundException("Student and Course Combination Does Not Exists");
        }
    }

    @Transactional
    @Override
    public void addStudCourses(
        long studentid,
        long courseid)
    {
        studentrepos.findById(studentid)
            .orElseThrow(() -> new ResourceNotFoundException("Student id " + studentid + " not found!"));
        courserepos.findById(courseid)
            .orElseThrow(() -> new ResourceNotFoundException("Course id " + courseid + " not found!"));

        if (courserepos.checkStudentCourseCombo(studentid,
            courseid)
            .getCount() <= 0)
        {
            courserepos.insertStudCourses("SYSTEM",
                studentid,
                courseid);
        } else
        {
            throw new EntityExistsException("Student and Course Combination Already Exists");
        }
    }

}
