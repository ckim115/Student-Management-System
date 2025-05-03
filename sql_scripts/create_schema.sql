CREATE TABLE `Students` (
  `studentID` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  PRIMARY KEY (`studentID`),
  UNIQUE KEY `studentID_UNIQUE` (`studentID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Courses` (
  `courseID` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(20) DEFAULT NULL,
  `instructor` varchar(20) DEFAULT NULL,
  `semester` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`courseID`),
  UNIQUE KEY `courseID_UNIQUE` (`courseID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Enrollments` (
  `enrollmentID` int NOT NULL AUTO_INCREMENT,
  `courseID` int DEFAULT NULL,
  `studentID` int DEFAULT NULL,
  `year` int DEFAULT NULL,
  PRIMARY KEY (`enrollmentID`),
  UNIQUE KEY `enrollmentID_UNIQUE` (`enrollmentID`),
  KEY `fk_student_idx` (`studentID`),
  KEY `fk_course_idx` (`courseID`),
  CONSTRAINT `fk_course` FOREIGN KEY (`courseID`) REFERENCES `Courses` (`courseID`) ON DELETE CASCADE,
  CONSTRAINT `fk_student` FOREIGN KEY (`studentID`) REFERENCES `Students` (`studentID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Grades` (
  `gradeID` int NOT NULL AUTO_INCREMENT,
  `studentID` int DEFAULT NULL,
  `courseID` int DEFAULT NULL,
  `grade` double DEFAULT NULL,
  `semester` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`gradeID`),
  UNIQUE KEY `gradeID_UNIQUE` (`gradeID`),
  KEY `fk2_student_idx` (`studentID`),
  KEY `fk2_course_idx` (`courseID`),
  CONSTRAINT `fk2_course` FOREIGN KEY (`courseID`) REFERENCES `Courses` (`courseID`) ON DELETE CASCADE,
  CONSTRAINT `fk2_student` FOREIGN KEY (`studentID`) REFERENCES `Students` (`studentID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
