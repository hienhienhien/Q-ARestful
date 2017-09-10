package com.project.v2.controller;

import com.project.repository.AnswerRepository;
import com.sun.xml.internal.ws.util.InjectionPlan;
import javax.inject.Inject;

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

import com.project.domain.Vote;
import com.project.repository.VoteRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController("answerControllerV2")
@RequestMapping("/v1/")
@Api(value = "answers", description = "Answers API version 1 ")
public class AnswerController {
	@Injection
	private AnswerRepository answerRepository;

	//when new answer
	@RequestMapping(value="/questions/{questionId}/answers",method=RequestMethod.POST
	@ApiOperation(value="Casts a new answer for a given question ",notes="The new created answer id will be sent in the location response header",
	response =Void.class)
	@ApiResponses(value = {@ApiResponse(code=201, message="Answer Created Successfully", response=Void.class) })
	public ResponseEntity<Void> createAnswer(@PathVariable Long questionId,@RequestBody Answer answer) {
		answer = answerRepository.save(answer);
		//set the headers for new created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);


	}
	//get all answers by question id 

	@RequestMapping(value="/questions/{questionId}/answers", method=RequestMethod.GET)
	@ApiOperation(value = "Retrieves all the answers", response=Answer.class, responseContainer="List")
	public Iterable<Answer> getAllAnswers(@PathVariable Long questionId,Pageable pageable) {
		return answerRepository.findByQuestion(questionId);
	}



}