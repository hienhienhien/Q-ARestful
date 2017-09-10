package com.project.client;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.domain.Option;
import com.project.domain.Question;

public class QuickQuestionClientV2 {

	private static final String QUICK_QUESTION_URI_2 = "http://localhost:8080/v2/questions";
	private RestTemplate restTemplate = new RestTemplate();
	
	public PageWrapper<Poll> getAllQuestions(int page, int size) {
		ParameterizedTypeReference<PageWrapper<Question>> responseType = new ParameterizedTypeReference<PageWrapper<Question>>() {};
		UriComponentsBuilder builder = UriComponentsBuilder
										.fromHttpUrl(QUICK_QUESTION_URI_2)
										.queryParam("page", page)
										.queryParam("size", size);
		
		ResponseEntity<PageWrapper<Question>> responseEntity = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null, responseType);
		PageWrapper<Poll> allQuestions = responseEntity.getBody();
		return allQuestions;
	}
	
	public Question getQuestionById(Long questionId) {
		return restTemplate.getForObject(QUICK_QUESTION_URI_2 + "/{questionId}", Question.class, questionId);
	}
	
	public URI createQuestion(Question question) {
		return restTemplate.postForLocation( QUICK_QUESTION_URI_2, question);
	}
	
	public void updateQuestion(Question question) {
		restTemplate.put(QUICK_QUESTION_URI_2 + "/{questionId}",  question, question.getId()); 
	}
	
	public void deleteQuestion(Long questionId) {
		restTemplate.delete(QUICK_QUESTION_URI_2 + "/{questionId}",  questionId);
	}
	
	public static void main(String[] args) {
		QuickQuestionClientV2 client = new QuickQuestionClientV2();
		
		
	}
	
	
}
