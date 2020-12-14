package com.lambdaschool.schools.controllers;

import com.lambdaschool.schools.models.Course;
import com.lambdaschool.schools.models.ErrorDetails;
import com.lambdaschool.schools.services.CoursesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping(value = "/courses")
public class CourseController
{
    @Autowired
    private CoursesService coursesService;

    @ApiOperation(value = "returns all Courses",
        response = Course.class,
        responseContainer = "List")
    @GetMapping(value = "/courses",
        produces = {"application/json"})
    public ResponseEntity<?> listAllCourses()
    {
        List<Course> myCourses = coursesService.findAll();
        return new ResponseEntity<>(myCourses,
            HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a course based off of the id",
        response = Course.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
        message = "Course Found",
        response = Course.class), @ApiResponse(code = 404,
        message = "Course Not Found",
        response = ErrorDetails.class)})
    @GetMapping(value = "/course/{courseId}",
        produces = {"application/json"})
    public ResponseEntity<?> getCourseById(
        @PathVariable
            Long courseId)
    {
        Course u = coursesService.findCourseById(courseId);
        return new ResponseEntity<>(u,
            HttpStatus.OK);
    }

    @PostMapping(value = "/course",
        consumes = {"application/json"})
    public ResponseEntity<?> addCourse(
        @Valid
        @RequestBody
            Course newcourse) throws
                              URISyntaxException
    {
        newcourse.setCourseid(0);
        newcourse = coursesService.save(newcourse);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCourseURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{courseid}")
            .buildAndExpand(newcourse.getCourseid())
            .toUri();
        responseHeaders.setLocation(newCourseURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

    @ApiOperation(value = "updates a course given in the request body",
        response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
        message = "Course Found",
        response = Void.class), @ApiResponse(code = 404,
        message = "Course Not Found",
        response = ErrorDetails.class)})
    @PutMapping(value = "/course/{courseid}",
        consumes = {"application/json"})
    public ResponseEntity<?> updateFullCourse(
        @Valid
        @RequestBody
            Course updateCourse,
        @PathVariable
            long courseid)
    {
        updateCourse.setCourseid(courseid);
        coursesService.save(updateCourse);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/course/{id}")
    public ResponseEntity<?> deleteCourseById(
        @PathVariable
            long id)
    {
        coursesService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
