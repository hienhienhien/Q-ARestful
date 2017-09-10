package com.project.client;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.domain.Option;
import com.project.domain.Question;

import sun.text.normalizer.NormalizerBase.QuickCheckResult;

public class QuickQuestionClient {

	private static final String QUICK_QUESTION_URI_V1 = "http://localhost:8080/v1/questions";
	
	private RestTemplate restTemplate = new RestTemplate();//rest template
	
//get one question by id
	public Question getQuestionById(Long questionId){
		return restTemplate.getForObject(QUICK_QUESTION_URI_V1+"/{questionId}",Question.class, questionId);
	}
//get all questions
	
	public List<Question> getAllQuestions() {
		ParameterizedTypeReference<List<Question>> responseType = new ParameterizedTypeReference<List<Question>>() {};
		ResponseEntity<List<Question>> responseEntity = restTemplate.exchange(QUICK_POLL_URI_V1, HttpMethod.GET, null, responseType);
		List<Question> allQuestions = responseEntity.getBody();

		return allQuestions;
	
	}


//create question 
	public URI createQuestion(Question question) {
		return restTemplate.postForLocation( QUICK_QUESTION_URI_V1, question);
	}
	//update question 
	public void updateQuestion(Question question) {
		restTemplate.put(QUICK_QUESTION_URI_V1 + "/{questionId}",  question, question.getId()); 
	}
	
	//delete by question id 
	
	public void deleteQuestion(Long questionId){
		restTemplate.delete(QUICK_QUESTION_URI_V1+"/{questionId}",questionId);
	}
	public static void main(String[] args) {
		QuickQuestionClient client = new QuickQuestionClient();
		
		// Test GetPoll
		
		Question question = client.getQuestionById(1L);
		System.out.println(question);
		
		// Test GetAllquestions
		List<Question> allQuestions = client.getAllQuestions();
		System.out.println(allQuestions);
		
		// Test Create question 
		Question newQuestion = new Question();
		newQuestion.setQuestion("What is your favourate color?");
		Set<Option> options = new HashSet<>();
		newQuestion.setOptions(options);
		
		Option option1 = new Option();
		option1.setValue("Red");
		options.add(option1);
		
		Option option2 = new Option();
		option2.setValue("Blue");
		options.add(option2);
		URI questionLocation = client.createQuestion(newQuestion);
		System.out.println("Newly Created question Location " + questionLocation);
		
		// Test Update question with Id 6
		Question questionForId6 = client.getQuestionById(6L);
		// Add a new Option
		Option newOption = new Option();
		newOption.setValue("The Incredibles");
		questionForId6.getOptions().add(newOption);
		
		client.updateQuestion(questionForId6);
		
		questionForId6 = client.getQuestionById(6L);
		System.out.println("Updated question has " + questionForId6.getOptions().size() + " options");
		
		// Test Delete
		client.deleteQuestion(5L);
	}
	
}
