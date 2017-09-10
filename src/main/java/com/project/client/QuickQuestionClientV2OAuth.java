package com.project.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import com.project.domain.Question;

public class QuickPollClientV2OAuth {

	private static final String QUICK_QUESTION_URI_V2 = "http://localhost:8080/oauth2/v2/questions";


	public Question getQuestionById(Long questionId) {
		OAuth2RestTemplate restTemplate = restTemplate();
		return restTemplate.getForObject(QUICK_QUESTION_URI_V2 + "/{questionId}", Question.class, questionId);
	}

	private OAuth2RestTemplate restTemplate() {
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setGrantType("password");
		resourceDetails.setAccessTokenUri("http://localhost:8080/oauth/token");
		resourceDetails.setClientId("quickquestioniOSClient");
		resourceDetails.setClientSecret("top_secret");
		
		// Set scopes
		List<String> scopes = new ArrayList<>();
		scopes.add("read"); scopes.add("write");
		resourceDetails.setScope(scopes);
		
		// Resource Owner details
		resourceDetails.setUsername("hien");
		resourceDetails.setPassword("pass");

		return new OAuth2RestTemplate(resourceDetails);
	}
	
	public static void main(String[] args) {
		QuickQuestionClientV2OAuth client = new QuickQuestionClientV2OAuth();
		
		Question question = client.getQuestionById(1L);
		System.out.println("Question is : " + question);
	}
}
