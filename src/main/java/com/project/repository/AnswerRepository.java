package com.project.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.domain.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long> {


	/**
	 * get all answer by question id 
	 */


	@Query(value="select a.* from Option o, Answer a where o.QUESTION_ID = ?1 and a.OPTION_ID = o.OPTION_ID", nativeQuery = true)
	public Iterable<Answer> findByQuestion(Long questionId);
	
}
