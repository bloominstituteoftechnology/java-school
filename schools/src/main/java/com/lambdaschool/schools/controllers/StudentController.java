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

@RestController
@RequestMapping(value = "/students")
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/students",
        produces = {"application/json"})
    public ResponseEntity<?> listAllStudents()
    {
        List<Student> myStudents = studentService.findAll();
        return new ResponseEntity<>(myStudents,
            HttpStatus.OK);
    }

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

    @DeleteMapping(value = "/student/{id}")
    public ResponseEntity<?> deleteStudentById(
        @PathVariable
            long id)
    {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
