package com.project.client;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class QuickQuestionClientV2BasicAuth {

	private static final String QUICK_QUESTION_URI_V2 = "http://localhost:8080/v2/questions";
	private RestTemplate restTemplate = new RestTemplate();
	
	public void deleteQuestion(Long questionId) {
		HttpHeaders authenticationHeaders = getAuthenticationHeader("admin", "admin");
		restTemplate.exchange(QUICK_QUESTION_URI_V2 + "/{questionId}", 
						HttpMethod.DELETE, new HttpEntity<Void>(authenticationHeaders), Void.class, questionId);
	}
	
	// Basic Authentication
	private HttpHeaders getAuthenticationHeader(String username, String password) {
			
		String credentials = username + ":" + password;
		byte[] base64CredentialData = Base64.encodeBase64(credentials.getBytes());
			
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic " + new String(base64CredentialData));
		
		return headers;
	}
	
	public static void main(String[] args) {
		QuickQuestionClientV3BasicAuth client = new QuickQuestionClientV3BasicAuth();
		
	}
		
}
