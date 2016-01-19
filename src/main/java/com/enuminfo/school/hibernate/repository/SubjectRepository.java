/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.Subject;

/**
 * @author Kumar
 */
public interface SubjectRepository extends PagingAndSortingRepository<Subject, Integer> {

	Subject findBySubjectName(String subjectName);
}
