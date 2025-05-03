SELECT * FROM sys.Students;
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Jazmin", "Pitts", "2002-01-04");
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Braylen", "Connor", "2000-02-08");
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Jaden", "Molina", "1999-03-12");
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Aspyn", "Griffith", "2004-04-16");
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Henrik", "Hudson", "2006-05-20");
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Dalary", "Avila", "2000-06-24");
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Frank", "Carpenter", "2002-07-28");
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Davis", "Hendricks", "2002-08-04");
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Vera", "Johns", "2003-09-08");
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Reed", "Reeves", "2004-10-12");
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Ryan", "Wilson", "2002-11-16");
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Troy", "Hunt", "2002-12-20");
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Milena", "Parrish", "2002-01-24");
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Nehemiah", "Vo", "2002-02-04");
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Saoirse", "Farrell", "2002-03-08");

SELECT * FROM sys.Courses;
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Python", "Smith", "Spring");
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Java", "Smith", "Spring");
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("C++", "Lee", "Fall");
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("C", "Langston", "Fall");
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Discrete Math", "Brown", "Fall");
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Discrete Math", "Lee", "Spring");
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Database Management", "Langston", "Spring");
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Java", "Lee", "Fall");
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Discrete Math", "Brown", "Spring");
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Creative Writing", "Davis", "Fall");
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Creative Writing", "Davis", "Spring");
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("C", "Smith", "Spring");
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Linear Algebra", "Smith", "Spring");
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Science Fiction", "Davis", "Winter");
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Linear Algebra", "Smith", "Summer");

SELECT * FROM sys.Enrollments;
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (5, 5, 2022);
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (5, 6, 2020);
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (6, 6, 2018);
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (7, 10, 2019);
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (8, 10, 2018);
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (11, 12, 2013);
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (6, 12, 2016);
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (6, 13, 2018);
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (7, 10, 2015);
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (7, 11, 2017);
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (11, 11, 2018);
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (12, 11, 2018);
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (13, 5, 2019);
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (13, 6, 2015);
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (13, 7, 2020);

SELECT * FROM sys.Grades;
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (5, 5, 90, "Fall");
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (6, 5, 100, "Fall");
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (6, 6, 80, "Fall");
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (10, 7, 65, "Fall");
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (10, 8, 89, "Spring");
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (12, 11, 75, "Spring");
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (12, 6, 73, "Fall");
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (13, 6, 27, "Fall");
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (10, 7, 45, "Fall");
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (11, 7, 56, "Fall");
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (11, 11, 98, "Spring");
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (11, 12, 85, "Fall");
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (5, 13, 66, "Fall");
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (6, 13, 100, "Fall");
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (7, 13, 95, "Fall");