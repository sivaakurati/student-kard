/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.Subject;

/**
 * @author Kumar
 */
public interface SubjectRepository extends PagingAndSortingRepository<Subject, Integer> {

	Subject findBySubjectName(String subjectName);
}
