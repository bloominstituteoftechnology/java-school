DELETE
FROM studcourses;

DELETE
FROM courses;

DELETE
FROM students;

DELETE
FROM instructors;

INSERT INTO instructors (instructorid, name, created_by, created_date, last_modified_by, last_modified_date)
    VALUES(1, 'Sally', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (2, 'Lucy', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (3, 'Charlie', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO courses (courseid, coursename, instructorid, created_by, created_date, last_modified_by, last_modified_date)
	VALUES (1, 'Data Science', 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 'JavaScript', 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (3, 'Node.js', 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (4, 'Java Back End', 2, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (5, 'Mobile IOS', 2, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (6, 'Careers', 3, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO students (studentid, name, created_by, created_date, last_modified_by, last_modified_date)
    VALUES (1, 'John', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 'Julian', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (3, 'Mary', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (4, 'Julia', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (5, 'Tyler', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (6, 'Kim', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (7, 'Juan', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (8, 'Robby', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (9, 'Roberto', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (10, 'Bob', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (11, 'Liz', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (12, 'June', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (13, 'April', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO studcourses (studentid, courseid, created_by, created_date, last_modified_by, last_modified_date)
    VALUES (1, 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (1, 4, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 2, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (3, 3, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (3, 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (3, 6, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

/*
We must tell hibernate the ids that have already been used.
The number must be larger than the last used id.
20 > 13 so we are good!
 */

alter sequence hibernate_sequence restart with 20;
