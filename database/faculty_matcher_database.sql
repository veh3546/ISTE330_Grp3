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

