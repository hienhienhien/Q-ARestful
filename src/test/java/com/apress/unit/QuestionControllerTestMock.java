package com.project.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.project.domain.Question;
import com.project.repository.QuestionRepository;
import com.project.v1.controller.QuestionController;
import com.google.common.collect.Lists;

public class QuestionControllerTestMock {

	@Mock
	private QuestionRepository questionRepository;
	
	@Before
    public void setUp() throws Exception {
    	MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGetAllQuestions() {
		QuestionController questionController  = new QuestionController();
    	ReflectionTestUtils.setField(questionController, "questionRepository", questionRepository);
    	
    	when(questionRepository.findAll()).thenReturn(new ArrayList<Question>());
		ResponseEntity<Iterable<Question>> allQuestionsEntity = questionController.getAllQuestions();
		verify(questionRepository, times(1)).findAll();
		assertEquals(HttpStatus.OK, allQuestionsEntity.getStatusCode());
		assertEquals(0, Lists.newArrayList(allQuestionsEntity.getBody()).size());
	}
	
}
