/**
    eSchool SQL Script (Dated:- Dec 24 2015)
    Creeated By Sivakumar.AKURATI
*/

-- tbl_location TABLE --
DROP TABLE IF EXISTS tbl_location;
CREATE TABLE tbl_location(
    location_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    location_name varchar(50) NOT NULL,
    pin_code int(11) NOT NULL,
    city_name varchar(50) NOT NULL,
    state_name varchar(50) NOT NULL
);

-- tbl_role TABLE --
DROP TABLE IF EXISTS tbl_role;
CREATE TABLE tbl_role(
    role_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    role_name varchar(50) NOT NULL
);

-- tbl_user TABLE --
DROP TABLE IF EXISTS tbl_user;
CREATE TABLE tbl_user(
    user_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_name varchar(50) NOT NULL,
    user_password varchar(20) NOT NULL
);

-- tbl_user_role TABLE --
DROP TABLE IF EXISTS tbl_user_role;
CREATE TABLE tbl_user_role(
    user_role_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id int(11) NOT NULL REFERENCES tbl_user (user_id),    
    role_id int(11) NOT NULL REFERENCES tbl_role (role_id)        
);

-- tbl_role TABLE --
DROP TABLE IF EXISTS tbl_location;
CREATE TABLE tbl_location(
    location_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    location_name varchar(50) NOT NULL,
    city_name varchar(50) NOT NULL,
    state_name varchar(50) NOT NULL,
    pin_code int(11) NOT NULL
);

-- tbl_course TABLE
DROP TABLE IF EXISTS tbl_course;
CREATE TABLE tbl_course(
    course_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    course_name varchar(50) NOT NULL
);

-- tbl_batch TABLE
DROP TABLE IF EXISTS tbl_batch;
CREATE TABLE tbl_batch(
    batch_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    batch_name varchar(50) NOT NULL
);

-- tbl_batch_course TABLE
DROP TABLE IF EXISTS tbl_batch_course;
CREATE TABLE tbl_batch_course(
    batch_course_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    batch_id int(11) NOT NULL REFERENCES tbl_batch (batch_id),    
    course_id int(11) NOT NULL REFERENCES tbl_course (course_id)
);

-- tbl_subject TABLE
DROP TABLE IF EXISTS tbl_subject;
CREATE TABLE tbl_subject(
    subject_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    subject_name varchar(50) NOT NULL
);

-- tbl_course_subject TABLE
DROP TABLE IF EXISTS tbl_course_subject;
CREATE TABLE tbl_course_subject(
    course_subject_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    course_id int(11) NOT NULL REFERENCES tbl_course (course_id),
    subject_id int(11) NOT NULL REFERENCES tbl_subject (subject_id)
);

-- tbl_teacher TABLE
DROP TABLE IF EXISTS tbl_teacher;
CREATE TABLE tbl_teacher(
    teacher_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    teacher_name varchar(50) NOT NULL,
    photo varchar(500) NOT NULL,
    email_address varchar(50) NOT NULL,
    address varchar(500) NOT NULL,
    location_id int(11) NOT NULL REFERENCES tbl_location (location_id),
    gender varchar(10) NOT NULL,
    contact_no int(10) NOT NULL,
    date_of_birth date NOT NULL,
    date_of_joining date NOT NULL,
    designation varchar(50) NOT NULL
);

-- tbl_teacher_subject TABLE
DROP TABLE IF EXISTS tbl_teacher_subject;
CREATE TABLE tbl_teacher_subject(
    teacher_subject_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    teacher_id int(11) NOT NULL REFERENCES tbl_teacher (teacher_id),
    subject_id int(11) NOT NULL REFERENCES tbl_subject (subject_id)
);

-- tbl_teacher_course TABLE
DROP TABLE IF EXISTS tbl_teacher_course;
CREATE TABLE tbl_teacher_course(
    teacher_course_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    teacher_id int(11) NOT NULL REFERENCES tbl_teacher (teacher_id),
    course_id int(11) NOT NULL REFERENCES tbl_course (course_id)
);

-- tbl_parent TABLE
DROP TABLE IF EXISTS tbl_parent;
CREATE TABLE tbl_parent(
    parent_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    parent_name varchar(50) NOT NULL,
    photo varchar(500) NOT NULL,
    gender varchar(10) NOT NULL,
    address varchar(500) NOT NULL,
    location_id int(11) NOT NULL REFERENCES tbl_location (location_id),
    contact_no int(10) NOT NULL,
    main_parent_id int(11) NULL,
    email_address varchar(50) NOT NULL
);

-- tbl_student TABLE
DROP TABLE IF EXISTS tbl_student;
CREATE TABLE tbl_student(
    student_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    student_name varchar(50) NOT NULL,
    photo varchar(500) NOT NULL,
    address varchar(500) NOT NULL,
    location_id int(11) NOT NULL REFERENCES tbl_location (location_id),
    gender varchar(10) NOT NULL,
    contact_no int(10) NOT NULL,
    date_of_birth date NOT NULL,
    date_of_joining date NOT NULL,
    batch_id int(11) NOT NULL REFERENCES tbl_batch (batch_id),
    course_id int(11) NOT NULL REFERENCES tbl_course (course_id),
    parent_id int(11) NOT NULL REFERENCES tbl_parent (parent_id)
);

-- tbl_time_tracker TABLE
DROP TABLE IF EXISTS tbl_time_tracker;
CREATE TABLE tbl_time_tracker(
	time_tracker_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	batch_id int(11) NOT NULL REFERENCES tbl_batch (batch_id),
    course_id int(11) NOT NULL REFERENCES tbl_course (course_id),
    subject_id int(11) NOT NULL REFERENCES tbl_subject (subject_id),
    start_date date NOT NULL,
    end_date date NOT NULL,
    full_day boolean NULL
);

-- tbl_time_tracker_event TABLE
DROP TABLE IF EXISTS tbl_time_tracker_event;
CREATE TABLE tbl_time_tracker_event(
	time_tracker_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	event_title varchar(500) NOT NULL,
    event_start varchar(20) NOT NULL,
    event_end varchar(20) NOT NULL,
    event_all_day boolean NULL
);

-- tbl_grade TABLE
DROP TABLE IF EXISTS tbl_grade;
CREATE TABLE tbl_grade(
	grade_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	student_id int(11) NOT NULL REFERENCES tbl_student (student_id),
    subject_id int(11) NOT NULL REFERENCES tbl_subject (subject_id),
    marks int(20) NOT NULL
);

-- tbl_assignment TABLE
DROP TABLE IF EXISTS tbl_assignment;
CREATE TABLE tbl_assignment(
	assignment_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	batch_id int(11) NOT NULL REFERENCES tbl_batch (batch_id),
    course_id int(11) NOT NULL REFERENCES tbl_course (course_id),
    subject_id int(11) NOT NULL REFERENCES tbl_subject (subject_id),
    assignment_question varchar(500) NOT NULL,
    last_submission_date date NULL
);

-- tbl_assignment_result TABLE
DROP TABLE IF EXISTS tbl_assignment_result;
CREATE TABLE tbl_assignment_result(
	assignment_result_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	assignment_id int(11) NOT NULL REFERENCES tbl_assignment (assignment_id),
    student_id int(11) NOT NULL REFERENCES tbl_student (student_id),
    result varchar(500) NOT NULL,
    submitted_date date NULL
);

-- tbl_department TABLE
DROP TABLE IF EXISTS tbl_department;
CREATE TABLE tbl_department(
	department_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	department_name varchar(500) NOT NULL
);
