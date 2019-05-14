package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "studentService")
public class StudentServiceImpl implements StudentService
{
    @Autowired
    private StudentRepository studrepos;

    @Override
    public List<Student> findAll(Pageable pageable)
    {
        List<Student> list = new ArrayList<>();
        studrepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Student findStudentById(long id) throws EntityNotFoundException
    {
        return studrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public List<Student> findStudentByNameLike(String name, Pageable pageable)
    {
        List<Student> list = new ArrayList<>();
        studrepos.findByStudnameContainingIgnoreCase(name, pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (studrepos.findById(id).isPresent())
        {
            studrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Student save(Student student)
    {
        Student newStudent = new Student();

        newStudent.setStudname(student.getStudname());

        return studrepos.save(newStudent);
    }

    @Override
    public Student update(Student student, long id)
    {
        Student currentStudent = studrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (student.getStudname() != null)
        {
            currentStudent.setStudname(student.getStudname());
        }

        return studrepos.save(currentStudent);
    }
}
