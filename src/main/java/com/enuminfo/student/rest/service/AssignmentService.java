/**
 * 
 */
package com.enuminfo.student.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.student.hibernate.model.Assignment;
import com.enuminfo.student.hibernate.model.AssignmentResult;
import com.enuminfo.student.hibernate.model.Student;
import com.enuminfo.student.hibernate.repository.AssignmentRespository;
import com.enuminfo.student.hibernate.repository.AssignmentResultRespository;
import com.enuminfo.student.hibernate.repository.BatchRepository;
import com.enuminfo.student.hibernate.repository.CourseRepository;
import com.enuminfo.student.hibernate.repository.StudentRepsitory;
import com.enuminfo.student.hibernate.repository.SubjectRepository;
import com.enuminfo.student.util.DateTimeUtil;

/**
 * @author Kumar
 */
@RestController
@RequestMapping(value = "/assignment")
public class AssignmentService {

	@Autowired BatchRepository batchRepository;
	@Autowired CourseRepository courseRepository;
	@Autowired SubjectRepository subjectRepository;
	@Autowired StudentRepsitory studentRepository;
	@Autowired AssignmentRespository repository;
	@Autowired AssignmentResultRespository assignmentResultRepository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Assignment> getAllAssignments() {
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveAssignment(@RequestBody Assignment assignment) {
		assignment.setBatch(batchRepository.findOne(assignment.getBatch().getBatchId()));
		assignment.setCourse(courseRepository.findOne(assignment.getCourse().getCourseId()));
		assignment.setSubject(subjectRepository.findOne(assignment.getSubject().getSubjectId()));
		assignment.setLastSubmissionDate(DateTimeUtil.convertSqlDate2UtilDate(assignment.getLsd()));
		repository.save(assignment);
	}
	
	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Assignment> getAllAssignmentsByStudent(@PathVariable Integer id) {
		Student student = studentRepository.findOne(id);
		return repository.findByBatchAndCourse(student.getBatch(), student.getCourse());
	}
	
	@RequestMapping(value = "/result/student/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<AssignmentResult> getAllResultsByStudent(@PathVariable Integer id) {
		Student student = studentRepository.findOne(id);
		return assignmentResultRepository.findByStudent(student);
	}
}
