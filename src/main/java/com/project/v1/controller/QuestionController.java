package com.project.v2.controller;

import com.sun.xml.internal.ws.util.InjectionPlan;
import java.net.URI;

import javax.inject.Inject;
import javax.naming.ldap.PagedResultsResponseControl;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.domain.Poll;
import com.project.dto.error.ErrorDetail;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.PollRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController("questionControllerV2")
@RequestMapping("/v1/")
@Api(value = "questions", description = "Question API version 1 ")
public class QuestionController {
	@Injection
	private QuestionRepository questionRepository;

	/**
	 * create a new question
	 */
	
	@RequestMapping(value="/questions", method=RequestMethod.POST)
	@ApiOperation(value = "Creates a new question", notes="The newly created question Id will be sent in the location response header", 
					response = Void.class)
	@ApiResponses(value = {@ApiResponse(code=201, message="question Created Successfully", response=Void.class),  
			@ApiResponse(code=500, message="Error creating question", response=ErrorDetail.class) } )
	public ResponseEntity<Void> createQuestion(@Valid @RequestBody Question question) {
		question  = questionRepository.save(question);
		
		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newQuestionUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId()).toUri();
		responseHeaders.setLocation(newQuestionUri);
		
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}



	/**
	 * get a question by id 
	 */

	@RequestMapping(value="/questions/{questionId}", method=RequestMethod.GET)
	@ApiOperation(value = "Retrieves given question", response=Poll.class)
	@ApiResponses(value = {@ApiResponse(code=200, message="get successfully", response=Question.class),  
			@ApiResponse(code=404, message="Unable to find question with the questionId", response=ErrorDetail.class) } )
	public ResponseEntity<?> getQuestion(@PathVariable Long questionId) {
		verifyQuestion(questionId);
		Question q = questionRepository.findOne(questionId);
		return new ResponseEntity<> (p, HttpStatus.OK);
	}


	/**
	 * get all questions
	 */

	
	@RequestMapping(value="/questions", method=RequestMethod.GET)
	@ApiOperation(value = "Retrieves all the questions", response=Question.class, responseContainer="List")
	public ResponseEntity<Page<Question>> getAllQuestions(Pageable pageable) {
		Page<Question> allPages = questionRepository.findAll(pageable);

		return new ResponseEntity<>(allPolls, HttpStatus.OK);
	}

	//edit one question by question id 

	@RequestMapping(value="/questions/{questionId}", method=RequestMethod.PUT)
	@ApiOperation(value = "Updates given question", response=Void.class)
	@ApiResponses(value = {@ApiResponse(code=200, message="edit question by id succesfully ", response=Void.class),  
			@ApiResponse(code=404, message="Unable to find question by given questionid", response=ErrorDetail.class) } )
	public ResponseEntity<Void> updateQuestion(@RequestBody Question question, @PathVariable Long questionId) {
		//verify the id first
		verifyQuestion(questionId);
		questionRepository.save(question);
		return new ResponseEntity<>(HttpStatus.OK);
	}



	//delete one question by question id 
	
	@RequestMapping(value="/questions/{questionId}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Deletes given question", response=Void.class)
	@ApiResponses(value = {@ApiResponse(code=200, message="successfully delete the question by questionid", response=Void.class),  
			@ApiResponse(code=404, message="Unable to find question id ", response=ErrorDetail.class) } )
	public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
		verifyQuestion(questionId);
		questionRepository.delete(questionId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	

	protected void verifyQuestion (Long questionId) throws ResourceNotFoundException {
		Question q = questionRepository.findOne(questionId);
		if(q == null) {
			throw new ResourceNotFoundException("Question with id " + questionId + " not found"); 
		}
	}
}
