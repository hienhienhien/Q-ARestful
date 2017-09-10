package com.project.it;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.project.QuickQuestionApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QuickQuestionApplication.class)
@WebAppConfiguration
public class QuestionControllerIT {

	@Inject
	private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
	
    @Before
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void testGetAllQuestions() throws Exception {
    	mockMvc.perform(get("/v1/questions"))
    			.andExpect(status().isOk())//expect ok 
    			.andExpect(jsonPath("$", hasSize(20)));
    }
}
