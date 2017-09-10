package com.project.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.proejct.domain.Question;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {

}
