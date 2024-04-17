DROP DATABASE IF EXISTS majors; 

CREATE DATABASE majors;

USE majors;

INSERT INTO majors (majorCode, major, majorLevel)
VALUES (1, "Web & Mobile Computing","Undergraduate"),
       (2, "Computing & Information Technologies","Undergraduate"),
       (3, "Networking & Systems Administration","Undergraduate"),
       (4, "Information Sciences & Technologies","Graduate"),
       (5, "Human Computer Interaction","Graduate"); 