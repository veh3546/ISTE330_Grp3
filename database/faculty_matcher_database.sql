DROP DATABASE IF EXISTS faculty_matcher; 

CREATE DATABASE faculty_matcher;

USE faculty_matcher;

CREATE TABLE faculty (
  userID INT,
  firstName VARCHAR(20),
  lastName VARCHAR(20),
  email VARCHAR(50),
  institution VARCHAR(50),
  office VARCHAR (20),
  department VARCHAR (20),
  PRIMARY KEY (userID)
);

CREATE TABLE majors (
  majorCode INT,
  major VARCHAR(50),
  majorLevel VARCHAR(20),
  PRIMARY KEY (majorCode)
);

CREATE TABLE students (
  userID INT,
  firstName VARCHAR(50),
  lastName VARCHAR(50),
  email VARCHAR(50),
  majorCode INT,
  FOREIGN KEY (majorCode) REFERENCES majors(majorCode),
  PRIMARY KEY (userID)
);

CREATE TABLE student_faculty_associations (
  studentID INT,
  facultyID INT,
  PRIMARY KEY (studentID, facultyID),
  FOREIGN KEY (facultyID) REFERENCES faculty(userID),
  FOREIGN KEY (studentID) REFERENCES students(userID)
);

CREATE TABLE account (
  userID INT,
  username VARCHAR(20),
  password VARCHAR(20),
  userType ENUM("faculty", "student"),
  PRIMARY KEY (userID)
);

CREATE TABLE interests (
  interestID INT,
  domain VARCHAR(50),
  interest VARCHAR(50),
  PRIMARY KEY (interestID)
);

CREATE TABLE user_interest_associations (
  userID INT ,
  interestID INT ,
  PRIMARY KEY (userID, interestID),
  FOREIGN KEY (interestID) REFERENCES interests(interestID)
);

CREATE TABLE abstract (
  abstractID INT,
  title VARCHAR(500),
  abstract VARCHAR(5000),
  PRIMARY KEY (abstractID)
);

CREATE TABLE faculty_abstract_associations (
  facultyID INT ,
  abstractID INT ,
  PRIMARY KEY (facultyID, abstractID),
  FOREIGN KEY (abstractID) REFERENCES abstract(abstractID),
  FOREIGN KEY (facultyID) REFERENCES faculty(userID)
);

-- SELECT * FROM user_interest_associations JOIN (SELECT * FROM user_interest_associations WHERE userID = 209) AS ilist USING(interestID) JOIN faculty ON user_interest_associations.userID = faculty.userID JOIN interests USING(interestID);

DROP PROCEDURE IF EXISTS match_faculty_interest;

DELIMITER $$
 
CREATE PROCEDURE match_faculty_interest(IN student_ID INT) 
   BEGIN
        SELECT faculty.firstName, faculty.lastName, faculty.email, faculty.institution, faculty.office, faculty.department, GROUP_CONCAT(interests.domain SEPARATOR ', '), GROUP_CONCAT(interests.interest SEPARATOR ', ')
          FROM user_interest_associations 
            JOIN (SELECT * FROM user_interest_associations WHERE userID = student_ID) AS ilist USING(interestID) 
              JOIN faculty ON user_interest_associations.userID = faculty.userID 
                JOIN interests USING(interestID)
                  GROUP BY faculty.firstName, faculty.lastName, faculty.email, faculty.institution, faculty.office, faculty.department;
   END$$
DELIMITER ;

-- SELECT * FROM user_interest_associations JOIN interests USING(interestID) JOIN abstract ON (abstract LIKE CONCAT('%',interest,'%') ) WHERE userID = 209

DROP PROCEDURE IF EXISTS match_faculty_abstract;

DELIMITER $$
 
CREATE PROCEDURE match_faculty_abstract(IN student_ID INT) 
   BEGIN
        SELECT faculty.userID, faculty.firstName, faculty.lastName, faculty.email, faculty.institution, faculty.office, faculty.department, GROUP_CONCAT(interests.domain SEPARATOR ', '), GROUP_CONCAT(interests.interest SEPARATOR ', ')
          FROM user_interest_associations 
            JOIN interests USING(interestID) 
              JOIN abstract ON (abstract LIKE CONCAT('%',interest,'%') ) 
                JOIN faculty_abstract_associations USING(abstractID) 
                  JOIN faculty ON(faculty.userID = faculty_abstract_associations.facultyID) 
                    WHERE user_interest_associations.userID = student_ID
                      GROUP BY faculty.userID, faculty.firstName, faculty.lastName, faculty.email, faculty.institution, faculty.office, faculty.department;
   END$$
DELIMITER ;


-- XX

DROP PROCEDURE IF EXISTS match_student_interest;

DELIMITER $$
 
CREATE PROCEDURE match_student_interest(IN faculty_ID INT)
   BEGIN
        SELECT students.firstName, students.lastName, students.email, GROUP_CONCAT(interests.domain SEPARATOR ', '), GROUP_CONCAT(interests.interest SEPARATOR ', ')
          FROM user_interest_associations 
            JOIN (SELECT * FROM user_interest_associations WHERE userID = faculty_ID) AS ilist USING(interestID) 
              JOIN students ON user_interest_associations.userID = students.userID 
                JOIN interests USING(interestID)
                  GROUP BY students.firstName, students.lastName, students.email;
   END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS get_abstracts;

DELIMITER $$
 
CREATE PROCEDURE get_abstracts(IN faculty_ID INT)
   BEGIN
        SELECT title, abstract
          FROM abstract 
            JOIN faculty_abstract_associations USING(abstractID)
              WHERE facultyID = faculty_ID;
   END$$
DELIMITER ;
