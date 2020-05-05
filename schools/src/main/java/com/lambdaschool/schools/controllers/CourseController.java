package com.lambdaschool.schools.controllers;

import com.lambdaschool.schools.models.Course;
import com.lambdaschool.schools.services.CoursesService;
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
 * The entry point for clients to work primary with courses
 */
@RestController
@RequestMapping(value = "/courses")
public class CourseController
{
    /**
     * Using the Courses service to process Course data
     */
    @Autowired
    private CoursesService coursesService;

    /**
     * Returns a list of all courses
     * <br>Example: <a href="http://localhost:2019/courses/courses">http://localhost:2019/courses/courses</a>
     *
     * @return JSON list of all courses with a status of OK
     * @see CoursesService#findAll() CoursesService.findAll()
     */
    @GetMapping(value = "/courses",
        produces = {"application/json"})
    public ResponseEntity<?> listAllCourses()
    {
        List<Course> myCourses = coursesService.findAll();
        return new ResponseEntity<>(myCourses,
            HttpStatus.OK);
    }

    /**
     * Returns a single course based off a course id number
     * <br>Example: <a href="http://localhost:2019/courses/course/7">http://localhost:2019/courses/course/7</a>
     *
     * @param courseId The primary key of the course you seek
     * @return JSON object of the course you seek
     * @see CoursesService#findCourseById(long) CoursesService.findCourseById(long)
     */
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

    /**
     * Given a complete Course Object, create a new Course record and student course records.
     * <br> Example: <a href="http://localhost:2019/courses/course">http://localhost:2019/courses/course</a>
     *
     * @param newcourse A complete new course to add including instructor and students.
     *                  instructor must already exist.
     *                  students must already exist.
     * @return A location header with the URI to the newly created course and a status of CREATED
     * @throws URISyntaxException Exception if something does not work in creating the location header
     * @see CoursesService#save(Course) CoursesService.save(Course)
     */
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

    /**
     * Given a complete Course Object
     * Given the course id, primary key, is in the Course table,
     * replace the Course record, student course combinations.
     * <br> Example: <a href="http://localhost:2019/courses/course/15">http://localhost:2019/courses/course/15</a>
     *
     * @param updateCourse A complete Course including all students. Students and Instructor must already exist.
     * @param courseid     The primary key of the course you wish to replace.
     * @return status of OK
     * @see CoursesService#save(Course) CoursesService.save(Course)
     */
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

    /**
     * Deletes a given course along with associated student course enrollments
     * <br>Example: <a href="http://localhost:2019/courses/course/14">http://localhost:2019/courses/courses/14</a>
     *
     * @param id the primary key of the course you wish to delete
     * @return Status of OK
     * @see CoursesService#delete(long) CoursesService.delete(long)
     */
    @DeleteMapping(value = "/course/{id}")
    public ResponseEntity<?> deleteCourseById(
        @PathVariable
            long id)
    {
        coursesService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
