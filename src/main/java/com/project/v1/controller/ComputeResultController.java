package com.project.v2.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import javax.xml.ws.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Answer;
import com.project.dto.OptionCount;
import com.project.dto.AnswerResult;
import com.project.repository.AnswerRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController("computeResultControllerV2")
@RequestMapping("/v1/")
@Api(value = "computeresult", description = "Compute Results API version 1")
public class ComputeResultController {
	
	@Inject
	private QuestionRepository questionRepository;

	
	@RequestMapping(value="/computeresult", method=RequestMethod.GET)
	@ApiOperation(value = "Computes the results of a given question", response = QuestionResult.class)
	public ResponseEntity<?> computeResult(@RequestParam Long questionId) {
		AnswerResult answerResult = new AnswerResult();
		Iterable<Answer> allAnswers = answerRepository.findByQuestion(questionId);
		/**
		 * algorithm to count the number of answers
		 * 
		 */

		 int totalAnswers = 0;
		 Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
		 for(Answer v :allAnswers) {
			 totalAnswers ++ ;
			 //get the optioncount corresponding to this option 
			 OptionCount optionCount = tempMap.get(a.getOption().getId());
			 if(optionCount == null) {
				 optionCount = new OptionCount();
				 optionCount.setOptionId(a.getOption().getId());
				 tempMap.put(a.getOption().getId(),optionCount);
			 }
			 optionCount.setCount(optionCount.getCount()+1);
		 }
		 answerResult.setTotalAnswers(totalAnswers);
		 answerResult.setResults(tempMap.values());

		 //return
		 return new ResponseEntity<AnswerResult>(answer);
	}
	
}