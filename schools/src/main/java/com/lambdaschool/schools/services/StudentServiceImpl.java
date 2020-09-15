package com.lambdaschool.schools.services;

import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.Course;
import com.lambdaschool.schools.models.StudCourses;
import com.lambdaschool.schools.models.Student;
import com.lambdaschool.schools.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * Implements the StudentService Interface
 */
@Service(value = "studentService")
public class StudentServiceImpl
    implements StudentService
{
    /**
     * Connects this service to the Student table.
     */
    @Autowired
    private StudentRepository studentrepos;

    /**
     * Connects this service to the course service.
     */
    @Autowired
    private CoursesService coursesService;

    @Override
    public List<Student> findAll()
    {
        List<Student> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        studentrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Student findStudentById(long id)
    {
        return studentrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student id " + id + " not found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        studentrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student id " + id + " not found!"));
        studentrepos.deleteById(id);
    }

    @Transactional
    @Override
    public Student save(Student student)
    {
        Student newStudent = new Student();

        if (student.getStudentid() != 0)
        {
            Student oldStudent = studentrepos.findById(student.getStudentid())
                .orElseThrow(() -> new ResourceNotFoundException("Student id " + student.getStudentid() + " not found!"));

            // delete the courses for the old student we are replacing
            for (StudCourses ur : oldStudent.getCourses())
            {
                coursesService.deleteStudentCourse(ur.getStudent()
                        .getStudentid(),
                    ur.getCourse()
                        .getCourseid());
            }
            newStudent.setStudentid(student.getStudentid());
        }

        newStudent.setName(student.getName());

        newStudent.getCourses()
            .clear();
        if (student.getStudentid() == 0)
        {
            for (StudCourses sc : student.getCourses())
            {
                Course newCourse = coursesService.findCourseById(sc.getCourse()
                    .getCourseid());

                newCourse.addStudent(newStudent);
            }
        } else
        {
            // add the new courses for the students we are replacing
            for (StudCourses sc : student.getCourses())
            {
                coursesService.addStudCourses(newStudent.getStudentid(), sc.getCourse().getCourseid());
            }
        }

        return studentrepos.save(newStudent);
    }
}
