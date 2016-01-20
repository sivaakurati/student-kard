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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tbl_role TABLE --
DROP TABLE IF EXISTS tbl_role;
CREATE TABLE tbl_user_role(
    role_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    role_name varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tbl_user TABLE --
DROP TABLE IF EXISTS tbl_user;
CREATE TABLE tbl_user(
    user_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_name varchar(50) NOT NULL,
    user_password varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tbl_user_role TABLE --
DROP TABLE IF EXISTS tbl_user_role;
CREATE TABLE tbl_user_role(
    user_role_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id int(11) NOT NULL REFERENCES tbl_user (user_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,    
    role_id int(11) NOT NULL REFERENCES tbl_role (role_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION        
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tbl_course TABLE
DROP TABLE IF EXISTS tbl_course;
CREATE TABLE tbl_course(
    course_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    course_name varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tbl_batch TABLE
DROP TABLE IF EXISTS tbl_batch;
CREATE TABLE tbl_batch(
    batch_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    batch_name varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tbl_batch_course TABLE
DROP TABLE IF EXISTS tbl_batch_course;
CREATE TABLE tbl_batch_course(
    batch_course_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    batch_id int(11) NOT NULL REFERENCES tbl_batch (batch_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,    
    course_id int(11) NOT NULL REFERENCES tbl_course (course_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tbl_subject TABLE
DROP TABLE IF EXISTS tbl_subject;
CREATE TABLE tbl_subject(
    subject_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    subject_name varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tbl_course_subject TABLE
DROP TABLE IF EXISTS tbl_course_subject;
CREATE TABLE tbl_course_subject(
    course_subject_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    course_id int(11) NOT NULL REFERENCES tbl_course (course_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    subject_id int(11) NOT NULL REFERENCES tbl_subject (subject_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tbl_teacher TABLE
DROP TABLE IF EXISTS tbl_teacher;
CREATE TABLE tbl_teacher(
    teacher_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    teacher_name varchar(50) NOT NULL,
    address varchar(500) NOT NULL,
    location_id int(11) NOT NULL REFERENCES tbl_location (location_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    contact_no int(10) NOT NULL,
    qualification varchar(50) NOT NULL,
    date_of_birth date NOT NULL,
    date_of_joining date NOT NULL,    
    subject_id int(11) NOT NULL REFERENCES tbl_subject (subject_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tbl_parent TABLE
DROP TABLE IF EXISTS tbl_parent;
CREATE TABLE tbl_parent(
    parent_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    parent_name varchar(50) NOT NULL,
    address varchar(500) NOT NULL,
    location_id int(11) NOT NULL REFERENCES tbl_location (location_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    contact_no int(10) NOT NULL,
    qualification varchar(50) NOT NULL,
    main_parent_id int(11) NULL REFERENCES tbl_parent (parent_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    relation varchar(50) NOT NULL,
    occupation varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tbl_teacher_payroll TABLE
DROP TABLE IF EXISTS tbl_teacher_payroll;
CREATE TABLE tbl_teacher_payroll(
    payroll_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    basic_salary int(20) NOT NULL,
    hra_percent int(11) NOT NULL,
    ta_percent int(11) NOT NULL,
    pf_percent int(11) NOT NULL,
    it_percent int(11) NOT NULL,
    teacher_id int(11) NOT NULL REFERENCES tbl_teacher (teacher_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tbl_student TABLE
DROP TABLE IF EXISTS tbl_student;
CREATE TABLE tbl_student(
    student_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    student_name varchar(50) NOT NULL,
    date_of_birth date NOT NULL,
    date_of_joining date NOT NULL,
    batch_id int(11) NOT NULL REFERENCES tbl_batch (batch_id)
    	ON DELETE NO ACTION ON UPDATE NO ACTION,
    course_id int(11) NOT NULL REFERENCES tbl_course (course_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    parent_id int(11) NOT NULL REFERENCES tbl_parent (parent_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tbl_student_installment TABLE
DROP TABLE IF EXISTS tbl_student_installment;
CREATE TABLE tbl_student_installment(
    installment_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    student_id int(11) NOT NULL REFERENCES tbl_student (student_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    admission_fee int(20) NOT NULL,
    admission_date date NOT NULL,
    first_fee int(20) NOT NULL,
    first_date date NOT NULL,
    second_fee int(20) NOT NULL,
    second_date date NOT NULL,
    third_fee int(20) NOT NULL,
    third_date date NOT NULL,
    miscellons_fee int(20) NOT NULL,
    miscellons_date date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;