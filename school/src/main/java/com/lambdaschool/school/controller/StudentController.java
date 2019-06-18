package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.ErrorDetail;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.StudentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "Return all Students", response = Student.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping(value = "/students", produces = {"application/json"})
    public ResponseEntity<?> listAllStudents(@PageableDefault(page = 0, size = 5) Pageable pageable)
    {
        List<Student> myStudents = studentService.findAll(pageable);
        return new ResponseEntity<>(myStudents, HttpStatus.OK);
    }

    @ApiOperation(value = "Return student with given Student ID", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully found student", response = Student.class),
            @ApiResponse(code = 404, message = "Could not find student", response = ErrorDetail.class)
    })
    @GetMapping(value = "/Student/{StudentId}",
                produces = {"application/json"})
    public ResponseEntity<?> getStudentById(@ApiParam(value = "Student ID", required = true, example = "1")
            @PathVariable
                    Long StudentId)
    {
        Student r = studentService.findStudentById(StudentId);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @ApiOperation(value = "Return students with a name containing entry", response = Student.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucessfully found students in given parameters", response = Student.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "An Error occurred while locating students", response = ErrorDetail.class)
    })
    @GetMapping(value = "/student/namelike/{name}",
                produces = {"application/json"})
    public ResponseEntity<?> getStudentByNameContaining(@ApiParam(value = "Student name Substring", required = true, example = "Jo")
            @PathVariable String name, @PageableDefault(page = 0, size = 5) Pageable pageable)
    {
        List<Student> myStudents = studentService.findStudentByNameLike(name, pageable);
        return new ResponseEntity<>(myStudents, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new student", notes = "URL for new student will be in location header", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created student", response = void.class),
            @ApiResponse(code = 500, message = "Failed to create student", response = ErrorDetail.class)
    })
    @PostMapping(value = "/Student",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewStudent(@Valid
                                           @RequestBody
                                                   Student newStudent) throws URISyntaxException
    {
        newStudent = studentService.save(newStudent);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newStudentURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Studentid}").buildAndExpand(newStudent.getStudid()).toUri();
        responseHeaders.setLocation(newStudentURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a current student", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated student", response = void.class),
            @ApiResponse(code = 500, message = "Failed to update student", response = ErrorDetail.class)
    })
    @PutMapping(value = "/Student/{Studentid}")
    public ResponseEntity<?> updateStudent(
            @RequestBody
                    Student updateStudent,
            @ApiParam(value = "Student ID", required = true, example = "1")
            @PathVariable
                    long Studentid)
    {
        studentService.update(updateStudent, Studentid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a current student", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Succesfully deleted student", response = void.class),
            @ApiResponse(code = 500, message = "Failed to delete student", response = ErrorDetail.class)
    })
    @DeleteMapping("/Student/{Studentid}")
    public ResponseEntity<?> deleteStudentById(@ApiParam(value = "Student ID", required = true)
            @PathVariable
                    long Studentid)
    {
        studentService.delete(Studentid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
