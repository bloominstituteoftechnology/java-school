# Project School

A student that completes this project shows that they can:

## Introduction

## Instructions

Starting with the java-school application

Get the endpoint GET /courses/studcount working

Add appropriate exception handling routines. Required exceptions to handle are when
  * a resource is not found
  * the wrong data type is used for a path variable
  * a non-handled endpoint is accessed (a URL not found exception)

Add appropriate logging routines. Required logging include
  * Activating actuator endpoints
  * Tomcat logging routed to a separate log file
  * Custom logging under each Get endpoint saying the endpoint has been accessed
    * should only go to console
    * for example when a client calls PUT /students/Student log should say "PUT /students/Student accessed"
    * include in log any appropriate parameters sent to the end point
    * for each log, add a date and time stamp.
  * Note: put the log files under the directory /tmp/var/logs/lambdajx You may have to create some directories for this to work.


## Stretch Goal
* Add endpoints to create, update, delete an instructor.
* Add endpoints to add, delete a student from a course.
* Add User Authentication using OAuth2 to the Java-School Application
* After adding User Authentication, Add Endpoints to create, read, update, delete a user.
