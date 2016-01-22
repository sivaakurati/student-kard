INSERT INTO TBL_ROLE ("ROLE_ID", "ROLE_NAME") VALUES (1,'ROLE_STUDENT'); -- STUDENT
INSERT INTO TBL_ROLE ("ROLE_ID", "ROLE_NAME") VALUES (2,'ROLE_ADMIN'); -- ADMIN
INSERT INTO TBL_ROLE ("ROLE_ID", "ROLE_NAME") VALUES (3,'ROLE_PARENT'); -- PARENT
INSERT INTO TBL_ROLE ("ROLE_ID", "ROLE_NAME") VALUES (4,'ROLE_TEACHER'); -- TEACHER

/* Password $2a$10$ggcRlrA5UqU.WLGtQAgL/ewqK0kSFRrGC3mtUV4Ut2k/X22YTvSwy matches "password" */
INSERT INTO TBL_USER ("USER_ID", "USER_NAME", "USER_PASSWORD") VALUES (1,'teacher@kard.com', 'p@5530rd');
INSERT INTO TBL_USER_ROLE ("USER_ROLE_ID", "USER_ID", "ROLE_ID") VALUES (1,1,4);

INSERT INTO TBL_USER ("USER_ID", "USER_NAME", "USER_PASSWORD") VALUES (2, 'admin@kard.com','p@5530rd');
INSERT INTO TBL_USER_ROLE ("USER_ROLE_ID", "USER_ID", "ROLE_ID") VALUES (2,2,1);
INSERT INTO TBL_USER_ROLE ("USER_ROLE_ID", "USER_ID", "ROLE_ID") VALUES (3,2,2);
INSERT INTO TBL_USER_ROLE ("USER_ROLE_ID", "USER_ID", "ROLE_ID") VALUES (4,2,3);
INSERT INTO TBL_USER_ROLE ("USER_ROLE_ID", "USER_ID", "ROLE_ID") VALUES (5,2,4);

INSERT INTO TBL_USER ("USER_ID", "USER_NAME", "USER_PASSWORD") VALUES (3,'parent@kard.com', 'p@5530rd');
INSERT INTO TBL_USER_ROLE ("USER_ROLE_ID", "USER_ID", "ROLE_ID") VALUES (6,3,3);

INSERT INTO TBL_USER ("USER_ID", "USER_NAME", "USER_PASSWORD") VALUES (4,'student@kard.com', 'p@5530rd');
INSERT INTO TBL_USER_ROLE ("USER_ROLE_ID", "USER_ID", "ROLE_ID") VALUES (7,4,1);

INSERT INTO TBL_SUBJECT ("SUBJECT_ID", "SUBJECT_NAME") VALUES (1, 'English');
INSERT INTO TBL_SUBJECT ("SUBJECT_ID", "SUBJECT_NAME") VALUES (2, 'Telugu');
INSERT INTO TBL_SUBJECT ("SUBJECT_ID", "SUBJECT_NAME") VALUES (3, 'Sanskrit');
INSERT INTO TBL_SUBJECT ("SUBJECT_ID", "SUBJECT_NAME") VALUES (4, 'Hindi');
INSERT INTO TBL_SUBJECT ("SUBJECT_ID", "SUBJECT_NAME") VALUES (5, 'Kannada');
INSERT INTO TBL_SUBJECT ("SUBJECT_ID", "SUBJECT_NAME") VALUES (6, 'Tamil');
INSERT INTO TBL_SUBJECT ("SUBJECT_ID", "SUBJECT_NAME") VALUES (7, 'French');
INSERT INTO TBL_SUBJECT ("SUBJECT_ID", "SUBJECT_NAME") VALUES (8, 'German');
INSERT INTO TBL_SUBJECT ("SUBJECT_ID", "SUBJECT_NAME") VALUES (9, 'Mathematics');
INSERT INTO TBL_SUBJECT ("SUBJECT_ID", "SUBJECT_NAME") VALUES (10, 'Physical Science');
INSERT INTO TBL_SUBJECT ("SUBJECT_ID", "SUBJECT_NAME") VALUES (11, 'Natural Science');
INSERT INTO TBL_SUBJECT ("SUBJECT_ID", "SUBJECT_NAME") VALUES (12, 'Social Studies');
INSERT INTO TBL_SUBJECT ("SUBJECT_ID", "SUBJECT_NAME") VALUES (13, 'Computer Science');

INSERT INTO TBL_COURSE ("COURSE_ID", "COURSE_NAME") VALUES (1, 'Course101');
INSERT INTO TBL_COURSE_SUBJECT ("COURSE_SUBJECT_ID", "COURSE_ID", "SUBJECT_ID") VALUES (1, 1, 1);
INSERT INTO TBL_COURSE_SUBJECT ("COURSE_SUBJECT_ID", "COURSE_ID", "SUBJECT_ID") VALUES (2, 1, 3);
INSERT INTO TBL_COURSE_SUBJECT ("COURSE_SUBJECT_ID", "COURSE_ID", "SUBJECT_ID") VALUES (3, 1, 5);

INSERT INTO TBL_COURSE ("COURSE_ID", "COURSE_NAME") VALUES (2, 'Course102');
INSERT INTO TBL_COURSE_SUBJECT ("COURSE_SUBJECT_ID", "COURSE_ID", "SUBJECT_ID") VALUES (4, 2, 7);
INSERT INTO TBL_COURSE_SUBJECT ("COURSE_SUBJECT_ID", "COURSE_ID", "SUBJECT_ID") VALUES (5, 2, 9);

INSERT INTO TBL_COURSE ("COURSE_ID", "COURSE_NAME") VALUES (3, 'Course103');
INSERT INTO TBL_COURSE_SUBJECT ("COURSE_SUBJECT_ID", "COURSE_ID", "SUBJECT_ID") VALUES (6, 3, 2);
INSERT INTO TBL_COURSE_SUBJECT ("COURSE_SUBJECT_ID", "COURSE_ID", "SUBJECT_ID") VALUES (7, 3, 4);
INSERT INTO TBL_COURSE_SUBJECT ("COURSE_SUBJECT_ID", "COURSE_ID", "SUBJECT_ID") VALUES (8, 3, 6);

INSERT INTO TBL_COURSE ("COURSE_ID", "COURSE_NAME") VALUES (4, 'Course105');
INSERT INTO TBL_COURSE_SUBJECT ("COURSE_SUBJECT_ID", "COURSE_ID", "SUBJECT_ID") VALUES (9, 4, 8);
INSERT INTO TBL_COURSE_SUBJECT ("COURSE_SUBJECT_ID", "COURSE_ID", "SUBJECT_ID") VALUES (10, 4, 10);

INSERT INTO TBL_BATCH ("BATCH_ID", "BATCH_NAME") VALUES (1, 'Batch10001');
INSERT INTO TBL_BATCH_COURSE ("BATCH_COURSE_ID", "BATCH_ID", "COURSE_ID") VALUES (1, 1, 1);
INSERT INTO TBL_BATCH_COURSE ("BATCH_COURSE_ID", "BATCH_ID", "COURSE_ID") VALUES (2, 1, 3);

INSERT INTO TBL_BATCH ("BATCH_ID", "BATCH_NAME") VALUES (2, 'Batch10003');
INSERT INTO TBL_BATCH_COURSE ("BATCH_COURSE_ID", "BATCH_ID", "COURSE_ID") VALUES (3, 2, 2);
INSERT INTO TBL_BATCH_COURSE ("BATCH_COURSE_ID", "BATCH_ID", "COURSE_ID") VALUES (4, 2, 4);
