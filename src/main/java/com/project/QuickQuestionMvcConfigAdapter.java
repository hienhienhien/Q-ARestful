package com.project;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class QuickQuestionMvcConfigAdapter extends WebMvcConfigurerAdapter {


	/**
	 * pageable 
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
	    PageableHandlerMethodArgumentResolver phmar = new PageableHandlerMethodArgumentResolver();
	    // Set the default size to 5
	    phmar.setFallbackPageable(new PageRequest(0, 5));//5
	    argumentResolvers.add(phmar);
	    super.addArgumentResolvers(argumentResolvers);
	}
}
