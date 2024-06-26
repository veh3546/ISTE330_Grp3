USE faculty_matcher;

INSERT INTO account (userID, username, password, userType)
VALUES  (101, "jrhicsa","123456", "faculty"),
        (102, "dan","123456", "faculty"),
        (103, "erik100","123456", "faculty"),
        (104, "andy368","123456", "faculty"),
        (105, "sam17","123456", "faculty"),
        (106, "andy111","123456", "faculty"),
        (107, "Gary142","123456", "faculty"),
        (208, "nan13","123456", "student"),
        (209, "abbie123","123456", "student"),
        (210, "james11","123456", "student"),
        (211, "smary_guy585","123456", "student"),
        (212, "jacob777","123456", "student"),
        (213, "jas55","123456", "student"),
        (214, "ty123","123456", "student");

INSERT INTO faculty (userID, firstName, lastName, email, institution, office, department)
VALUES (101, "James","Habermas", "jrhicsa@rit.edu", "RIT", 'GOL-2556', 'IS'),
       (102, "Dan","Dogaard", "dan.bogaard@rit.edu", "RIT", 'GOL-1001', 'IS'),
       (103, "Erik","Golen", "efgics@rit.edu", "RIT", 'GOL-1002', 'IS'),
       (104, "Andrew","Meneely", "andy.meneely@rit.edu", "RIT", 'GOL-1003', 'SE'),
       (105, "Samuel","MalacMalachowsky", "samvse@rit.edu", "RIT", 'GOL-1004', 'SE'),
       (106, "Andrew","Dimock", "awdics@rit.edu", "RIT", 'GOL-1005', 'IS'),
       (107, "Garret","Arcoraci", "gpavks@rit.edu", "RIT", 'GOL-1006', 'IS');

INSERT INTO majors (majorCode, major, majorLevel)
VALUES (4523, "Web & Mobile Computing","Undergraduate"),
       (1634, "Computing & Information Technologies","Undergraduate"),
       (9063, "Networking & Systems Administration","Undergraduate"),
       (8012, "Information Sciences & Technologies","Graduate"),
       (9513, "Human Computer Interaction","Graduate");

INSERT INTO students (userID, firstName, lastName, email, majorCode)
VALUES  (208, "Nanny","Li", "nitroacid@rit.edu", 4523),
        (209, "Abbie","Woo", "Abbiewoo@rit.edu", 1634),
        (210, "James","Olive", "JamesOlive@rit.edu", 1634),
        (211, "Andrew","Hongs", "andrewhong@rit.edu", 9063),
        (212, "Jacob","Flaa", "jacobflaa@rit.edu", 9063),
        (213, "Jasmine","Chen", "jasinerice@rit.edu", 8012),
        (214, "Ty","Lee", "tylee@rit.edu", 9513);

INSERT INTO interests (interestID, domain, interest)
VALUES  (1, 'Software Engineering','Web development'),
        (2, 'Software Engineering','Frontend Development'),
        (3, 'Databases','Relational Databases'),
        (4, 'Databases','Document Oriented Databases'),
        (5, 'Cyber-Security','Encryption'),
        (6, 'Software Engineering','2D Game Design'),
        (7, 'Software Engineering','Embedded Systems'),
        (8, 'Education','Accessibility Education');

INSERT INTO abstract (abstractID, title, abstract)
VALUES (1, "Building a Growth Mindset Toolkit as a Means Toward
Developing a Growth Mindset for Faculty Interactions with
Students In and Out of the Classroom",
"During the spring semester of the 2021 academic year, a group of
faculty gathered as part of a Growth Mindset Faculty Community of
Practice (GM-FCoP) to understand how to use a growth mindset to
positively impact students in their courses, through mentoring and
in daily conversations. Grounded in Carol Dweck’s seminal works
on theories of intelligence, a growth mindset asserts that skills can
be developed over time and views challenges as opportunities for
growth and future success. This contrasts with a fixed mindset
which views skills as set at birth with little hope for development.
This notion of a fixed mindset also contrasts with the essence of our
work as faculty and educators where we strive daily to positively
influence our students’ successes, learning and skill development.
Yet, embracing a growth mindset over a fixed mindset can prove
challenging. Learning about a growth mindset serves as an effective
starting point for faculty, with next steps revolving around actively
generating their own knowledge toward an overarching goal of
applying the growth mindset concepts in their coursework as well
as while mentoring students. This experience paper outlines the
GM-FCoP’s creation of a Growth Mindset Toolkit to serve as a
resource for faculty as they foster and promote a growth mind-
set with students in formal settings such as in the classroom and
in mentoring sessions, as well as informal settings such as office
hours and general conversations and interactions. Faculty devel-
oped approaches for a growth mindset are highlighted along with
leadership reflections and next steps."),
       
(2, "An Underwater Sensor Allocation Scheme for Noncircular Sensing Coverage Regions",
"Intelligently allocating underwater sensors to a large area of interest whose acoustic 
characteristics vary throughout is a challenge, especially for an area clearance scenario.
In these scenarios, there is no apparent target for an adversary to gravitate towards, 
such as a ship or a port. Thus, it is difficult to determine how the field designer should 
allocate sensors so that their deployment locations can be planned efficiently. The 
previously proposed Game Theory Field Design (GTFD) model can achieve an intelligent 
sensor allocation, using a game theoretic approach, for sensors with circular coverage
regions. In practice, however, the sensing coverage of an underwater sensor will likely
be noncircular due to the azimuthally dependent bathymetric phenomena and other underwater
irregularities. As a result, an extension of the model is proposed for allocating sensors 
for the irregularly shaped sensing coverage regions. This work provides two validations of 
the extended GTFD model. The first is an analytical comparison with sensing coverage regions
whose shape is well understood, and the second uses simulation to validate the model for 
the irregularly shaped regions."),

(3, "Predicting failures with developer networks and social network analysis",
"Software fails and fixing it is expensive. Research in failure
prediction has been highly successful at modeling software
failures. Few models, however, consider the key cause of failures
in software: people. Understanding the structure of developer
collaboration could explain a lot about the reliability of the final
product. We examine this collaboration structure with the
developer network derived from code churn information that can
predict failures at the file level. We conducted a case study
involving a mature Nortel networking product of over three
million lines of code. Failure prediction models were developed
using test and post-release failure data from two releases, then
validated against a subsequent release. One model’s prioritization
revealed 58% of the failures in 20% of the files compared with the
optimal prioritization that would have found 61% in 20% of the
files, indicating that a significant correlation exists between file-
based developer network metrics and failures."),

(4, "Presenting and Evaluating the Impact of Experiential Learning in Computing Accessibility Education",
"Studies indicate that much of the software created today is not accessible to all users, 
indicating that developers don't see the need to devote sufficient resources to creating 
accessible software. Compounding this problem, there is a lack of robust, easily adoptable
educational accessibility material available to instructors for inclusion in their 
curricula. To address these issues, we have created five Accessibility Learning Labs (ALL)
using an experiential learning structure. The labs are designed to educate and create 
awareness of accessibility needs in computing. The labs enable easy classroom integration
by providing instructors with complete educational materials including lecture slides, 
activities, and quizzes. The labs are hosted on our servers and require only a browser 
to be utilized. To demonstrate the benefit of our material and the potential benefits 
of our experiential lab format with empathy-creating material, we conducted a study 
involving 276 students in ten sections of an introductory computing course. Our findings
include: (I) The demonstrated potential of the proposed experiential learning format and
labs are effective in motivating and educating students about the importance of accessibility
(II) The labs are effective in informing students about foundational accessibility topics
(III) Empathy-creating material is demonstrated to be a beneficial component in computing
accessibility education, supporting students in placing a higher value on the importance
of creating accessible software. Created labs and project materials are publicly
available on the project website: http://all.rit.edu");

INSERT INTO faculty_abstract_associations (facultyID, abstractID)
VALUES (102, 1),
       (103, 2),
       (104, 3),
       (105, 4),
       (104, 4);

INSERT INTO user_interest_associations (userID, interestID)
VALUES (102, 7),
       (103, 2),
       (104, 3),
       (105, 4),
       (105, 3),
       (208, 1),
       (208, 2),
       (209, 3),
       (209, 4),
       (209, 8);



