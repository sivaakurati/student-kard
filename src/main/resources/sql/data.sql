insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10805,'Bangalore','A F Hospital',560007,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10808,'Bangalore','Abbur',562108,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10821,'Bangalore','Adugodi',560030,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10835,'Bangalore','Agrahara Dasarahalli',560079,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10859,'Bangalore','Akkur',562124,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10912,'Bangalore','Anand Nagar',560024,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10935,'Bangalore','Arabic College',560045,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10941,'Bangalore','Aranya Bhavan',560003,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10964,'Bangalore','Artillery Road',560008,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10965,'Bangalore','Arunachalam Mudaliar Street',560051,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10970,'Bangalore','Ashok Nagar',560050,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10986,'Bangalore','Attibele',562107,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10993,'Bangalore','Austin Town',560047,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10995,'Bangalore','Avani Sringeri Mutt',560086,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10997,'Bangalore','Avathi',562136,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (10999,'Bangalore','Avenue Road',560002,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11024,'Bangalore','Bagal',562149,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11060,'Bangalore','Balappa A Street',560051,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11069,'Bangalore','Balepet',560053,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11084,'Bangalore','Banashankari',560050,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11085,'Bangalore','Banashankari Llst',560070,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11086,'Bangalore','Banashankjari Iiist',560085,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11087,'Bangalore','Banaswadi',560043,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11094,'Bangalore','Bangalore',560002,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11095,'Bangalore','Bangalore',560044,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11096,'Bangalore','Bangalore Airport H A L',560017,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11097,'Bangalore','Bangalore Bazar',560001,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11098,'Bangalore','Bangalore City H.O',560002,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11099,'Bangalore','Bangalore Dist Office Bui',560009,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11100,'Bangalore','Bangalore Fort',560002,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11101,'Bangalore','Bangalore G P O',560001,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11111,'Bangalore','Bannerghata Road',560076,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11112,'Bangalore','Bannerghatta',560083,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11133,'Bangalore','Whitefield',560066,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11134,'Bangalore','Vathur',560087,'Karnataka');
insert into tbl_location (location_id,city_name,location_name,pin_code,state_name) values (11139,'Bangalore','Basavaraja Market',560002,'Karnataka');

insert into tbl_role (role_id, role_name) values (1,'ROLE_STUDENT'); -- STUDENT
insert into tbl_role (role_id, role_name) values (2,'ROLE_ADMIN'); -- ADMIN
insert into tbl_role (role_id, role_name) values (3,'ROLE_PARENT'); -- PARENT
insert into tbl_role (role_id, role_name) values (4,'ROLE_EMPLOYEE'); -- EMPLOYEE

/* Password $2a$10$ggcRlrA5UqU.WLGtQAgL/ewqK0kSFRrGC3mtUV4Ut2k/X22YTvSwy matches 'password' */
insert into tbl_user (user_id, user_name, user_password) values (1, 'admin@kard.com','p@5530rd');
insert into tbl_user_role (user_role_id, user_id, role_id) values (1,1,2);
insert into tbl_user_role (user_role_id, user_id, role_id) values (13,1,1);
insert into tbl_user_role (user_role_id, user_id, role_id) values (14,1,3);
insert into tbl_user_role (user_role_id, user_id, role_id) values (15,1,4);

insert into tbl_subject (subject_id, subject_name) values (1, 'English');
insert into tbl_subject (subject_id, subject_name) values (2, 'Telugu');
insert into tbl_subject (subject_id, subject_name) values (3, 'Sanskrit');
insert into tbl_subject (subject_id, subject_name) values (4, 'Hindi');
insert into tbl_subject (subject_id, subject_name) values (5, 'Kannada');
insert into tbl_subject (subject_id, subject_name) values (6, 'Tamil');
insert into tbl_subject (subject_id, subject_name) values (7, 'French');
insert into tbl_subject (subject_id, subject_name) values (8, 'German');
insert into tbl_subject (subject_id, subject_name) values (9, 'Mathematics');
insert into tbl_subject (subject_id, subject_name) values (10, 'Physical Science');
insert into tbl_subject (subject_id, subject_name) values (11, 'Natural Science');
insert into tbl_subject (subject_id, subject_name) values (12, 'Social Studies');
insert into tbl_subject (subject_id, subject_name) values (13, 'Computer Science');

insert into tbl_course (course_id, course_name) values (1, 'Course101');
insert into tbl_course_subject (course_subject_id, course_id, subject_id) values (1, 1, 1);
insert into tbl_course_subject (course_subject_id, course_id, subject_id) values (2, 1, 3);
insert into tbl_course_subject (course_subject_id, course_id, subject_id) values (3, 1, 5);

insert into tbl_course (course_id, course_name) values (2, 'Course102');
insert into tbl_course_subject (course_subject_id, course_id, subject_id) values (4, 2, 7);
insert into tbl_course_subject (course_subject_id, course_id, subject_id) values (5, 2, 9);

insert into tbl_course (course_id, course_name) values (3, 'Course103');
insert into tbl_course_subject (course_subject_id, course_id, subject_id) values (6, 3, 2);
insert into tbl_course_subject (course_subject_id, course_id, subject_id) values (7, 3, 4);
insert into tbl_course_subject (course_subject_id, course_id, subject_id) values (8, 3, 6);

insert into tbl_course (course_id, course_name) values (4, 'Course105');
insert into tbl_course_subject (course_subject_id, course_id, subject_id) values (9, 4, 8);
insert into tbl_course_subject (course_subject_id, course_id, subject_id) values (10, 4, 10);

insert into tbl_batch (batch_id, batch_name) values (1, 'Batch10001');
insert into tbl_batch_course (batch_course_id, batch_id, course_id) values (1, 1, 1);
insert into tbl_batch_course (batch_course_id, batch_id, course_id) values (2, 1, 3);

insert into tbl_batch (batch_id, batch_name) values (2, 'Batch10003');
insert into tbl_batch_course (batch_course_id, batch_id, course_id) values (3, 2, 2);
insert into tbl_batch_course (batch_course_id, batch_id, course_id) values (4, 2, 4);

insert into tbl_department (department_id, department_name) values (1, 'Curriculum');
insert into tbl_department (department_id, department_name) values (2, 'Finance');
insert into tbl_department (department_id, department_name) values (3, 'Transport');

insert into tbl_teacher (teacher_id, teacher_name, gender, email_address, address, location_id, contact_no, date_of_birth, date_of_joining, designation) values 
	(3, 'Admin', 'male', 'admin@kard.com', 'Gandhi Nagar', 11133, 1234567890, '1983-08-01', '2008-12-26', 'Admin');

insert into tbl_teacher (teacher_id, teacher_name, gender, email_address, address, location_id, contact_no, date_of_birth, date_of_joining, designation) values 
	(1, 'Sekhar', 'male', 'sekhar@kard.com', 'Gandhi Nagar', 11133, 1234567890, '1983-08-01', '2008-12-26', 'Head Of Dept');
insert into tbl_teacher_subject (teacher_subject_id, teacher_id, subject_id) values (1, 1, 1);
insert into tbl_teacher_subject (teacher_subject_id, teacher_id, subject_id) values (2, 1, 3);
insert into tbl_teacher_subject (teacher_subject_id, teacher_id, subject_id) values (3, 1, 5);
insert into tbl_teacher_course (teacher_course_id, teacher_id, course_id) values (1, 1, 1);
insert into tbl_teacher_course (teacher_course_id, teacher_id, course_id) values (2, 1, 3);
insert into tbl_user (user_id, user_name, user_password) values (5, 'sekhar@kard.com','p@5530rd');
insert into tbl_role (role_id, role_name) values (5,'ROLE_CURRICULUM'); -- CURRICULUM
insert into tbl_user_role (user_role_id, user_id, role_id) values (2,5,4);
insert into tbl_user_role (user_role_id, user_id, role_id) values (3,5,5);

insert into tbl_teacher (teacher_id, teacher_name, gender, email_address, address, location_id, contact_no, date_of_birth, date_of_joining, designation) values 
	(2, 'Madhu', 'female', 'madhu@kard.com', 'Immadihalli', 11133, 1234567890, '1987-08-01', '2010-12-26', 'Faculity');
insert into tbl_teacher_subject (teacher_subject_id, teacher_id, subject_id) values (4, 2, 7);
insert into tbl_teacher_subject (teacher_subject_id, teacher_id, subject_id) values (5, 2, 9);
insert into tbl_user (user_id, user_name, user_password) values (6, 'madhu@kard.com','p@5530rd');
insert into tbl_user_role (user_role_id, user_id, role_id) values (4,6,4);

insert into tbl_parent (parent_id, parent_name, gender, email_address, address, location_id, contact_no, main_parent_id) values 
	(1, 'Venkatswara Rao', 'male', 'venkatswara@kard.com', 'Gandhi Nagar', 11133, 1234567890, null);
insert into tbl_user (user_id, user_name, user_password) values (7, 'venkatswara@kard.com','p@5530rd');
insert into tbl_user_role (user_role_id, user_id, role_id) values (5,7,3);

insert into tbl_parent (parent_id, parent_name, gender, email_address, address, location_id, contact_no, main_parent_id) values 
	(2, 'Naga Lakshmi', 'female', 'lakshmi@kard.com', 'Gandhi Nagar', 11133, 1234567890, 1);
insert into tbl_user (user_id, user_name, user_password) values (8, 'lakshmi@kard.com','p@5530rd');
insert into tbl_user_role (user_role_id, user_id, role_id) values (6,8,3);

insert into tbl_parent (parent_id, parent_name, gender, email_address, address, location_id, contact_no, main_parent_id) values 
	(3, 'Appa Rao', 'male', 'appa@kard.com', 'Gandhi Nagar', 11133, 1234567890, 1);
insert into tbl_user (user_id, user_name, user_password) values (9, 'appa@kard.com','p@5530rd');
insert into tbl_user_role (user_role_id, user_id, role_id) values (7,9,3);

insert into tbl_student (student_id, student_name, gender, email_address, address, date_of_birth, date_of_joining, location_id, contact_no, batch_id, course_id, parent_id) values 
	(1, 'Phani', 'male', 'phani@kard.com', 'Gandhi Nagar', '1998-02-01', '2015-06-26', 11133, 1234567890, 1, 1, 1);
insert into tbl_user (user_id, user_name, user_password) values (10, 'phani@kard.com','p@5530rd');
insert into tbl_user_role (user_role_id, user_id, role_id) values (8,10,1);

insert into tbl_student (student_id, student_name, gender, email_address, address, date_of_birth, date_of_joining, location_id, contact_no, batch_id, course_id, parent_id) values 
	(2, 'Swathi', 'female', 'swathi@kard.com', 'Gandhi Nagar', '1999-02-01', '2016-06-26', 11133, 1234567890, 1, 3, 1);
insert into tbl_user (user_id, user_name, user_password) values (11, 'swathi@kard.com','p@5530rd');
insert into tbl_user_role (user_role_id, user_id, role_id) values (12,11,1);

insert into tbl_assignment (assignment_id, batch_id, course_id, subject_id, assignment_question, last_submission_date) values (1, 1, 1, 1, 'english question', '2016-03-18');
insert into tbl_assignment (assignment_id, batch_id, course_id, subject_id, assignment_question, last_submission_date) values (2, 1, 1, 3, 'sanskrit question', '2016-03-20');
insert into tbl_assignment (assignment_id, batch_id, course_id, subject_id, assignment_question, last_submission_date) values (3, 1, 1, 5, 'kannada question', '2016-03-22');

insert into tbl_assignment (assignment_id, batch_id, course_id, subject_id, assignment_question, last_submission_date) values (4, 2, 2, 7, 'french question', '2016-04-07');
insert into tbl_assignment (assignment_id, batch_id, course_id, subject_id, assignment_question, last_submission_date) values (5, 2, 2, 9, 'maths question', '2016-04-10');
