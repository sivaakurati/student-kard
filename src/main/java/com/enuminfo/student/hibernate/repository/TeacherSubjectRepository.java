/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.TeacherSubject;

/**
 * @author Kumar
 */
public interface TeacherSubjectRepository extends PagingAndSortingRepository<TeacherSubject, Integer> {

	@Query ("SELECT model.subject.subjectId FROM TeacherSubject model WHERE model.teacher.teacherId = ?1")
	Iterable<Integer> findByTeacherId(Integer teacherId);
}
