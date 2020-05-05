package com.lambdaschool.schools.controllers;

import com.lambdaschool.schools.models.Student;
import com.lambdaschool.schools.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * The entry point for clients to work primary with student data
 */
@RestController
@RequestMapping(value = "/students")
public class StudentController
{
    /**
     * Using the Student service to process Student data
     */
    @Autowired
    private StudentService studentService;

    /**
     * Returns a list of all students
     * <br>Example: <a href="http://localhost:2019/students/students">http://localhost:2019/students/students</a>
     *
     * @return JSON list of all students with a status of OK
     * @see StudentService#findAll() StudentService.findAll()
     */
    @GetMapping(value = "/students",
        produces = {"application/json"})
    public ResponseEntity<?> listAllStudents()
    {
        List<Student> myStudents = studentService.findAll();
        return new ResponseEntity<>(myStudents,
            HttpStatus.OK);
    }

    /**
     * Returns a single user based off a student id number
     * <br>Example: <a href="http://localhost:2019/students/student/7">http://localhost:2019/students/student/7</a>
     *
     * @param studentId The primary key of the student you seek
     * @return JSON object of the student you seek
     * @see StudentService#findStudentById(long) StudentService.findStudentById(long)
     */
    @GetMapping(value = "/student/{studentId}",
        produces = {"application/json"})
    public ResponseEntity<?> getStudentById(
        @PathVariable
            Long studentId)
    {
        Student u = studentService.findStudentById(studentId);
        return new ResponseEntity<>(u,
            HttpStatus.OK);
    }

    /**
     * Given a complete student Object, create a new student record and student course records.
     * <br> Example: <a href="http://localhost:2019/students/student">http://localhost:2019/students/student</a>
     *
     * @param newStudent A complete new student to add including enrolled courses.
     *                   Courses must already exist.
     * @return A location header with the URI to the newly created student and a status of CREATED
     * @throws URISyntaxException Exception if something does not work in creating the location header
     * @see StudentService#save(Student) StudentService.save(User)
     */
    @PostMapping(value = "/student",
        consumes = {"application/json"})
    public ResponseEntity<?> addStudentUser(
        @Valid
        @RequestBody
            Student newStudent) throws
                                URISyntaxException
    {
        newStudent.setStudentid(0);
        newStudent = studentService.save(newStudent);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{studentid}")
            .buildAndExpand(newStudent.getStudentid())
            .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

    /**
     * Given a complete student Object
     * Given the student id, primary key, is in the student table,
     * replace the student record, student course combinations.
     * <br> Example: <a href="http://localhost:2019/students/student/15">http://localhost:2019/students/student/15</a>
     *
     * @param updateStudent A complete student including all students. Students and Instructor must already exist.
     * @param studentid     The primary key of the student you wish to replace.
     * @return status of OK
     * @see StudentService#save(Student) StudentService.save(Student)
     */
    @PutMapping(value = "/student/{studentid}",
        consumes = {"application/json"})
    public ResponseEntity<?> updateFullstudent(
        @Valid
        @RequestBody
            Student updateStudent,
        @PathVariable
            long studentid)
    {
        updateStudent.setStudentid(studentid);
        studentService.save(updateStudent);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Deletes a given student along with associated student course enrollments
     * <br>Example: <a href="http://localhost:2019/students/student/14">http://localhost:2019/students/students/14</a>
     *
     * @param id the primary key of the student you wish to delete
     * @return Status of OK
     * @see StudentService#delete(long) StudentService.delete(long)
     */
    @DeleteMapping(value = "/student/{id}")
    public ResponseEntity<?> deleteStudentById(
        @PathVariable
            long id)
    {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
