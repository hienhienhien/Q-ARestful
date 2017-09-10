package com.project.unit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.project.QuickQuestionApplication;
import com.project.domain.Question;
import com.project.repository.QuestionRepository;
import com.project.v1.controller.QuestionController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QuickQuestionApplication.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class QuestionControllerTest {
	@Mock
	private QuestionRepository questionRepository;
	
	@InjectMocks
	QuestionController questionController;

	private MockMvc mockMvc;
	
    @Before
    public void setUp() throws Exception {
    	MockitoAnnotations.initMocks(this);
    	mockMvc = standaloneSetup(pollController).build();
    }
    
    @Test
    public void testGetAllQuestions() throws Exception {
    	when(questionRepository.findAll()).thenReturn(new ArrayList<Question>());
    	mockMvc.perform(get("/v1/questions"))
    			.andExpect(status().isOk())
    			.andExpect(content().string("[]")); 
    }
}
