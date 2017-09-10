package com.project;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * oauth 2 resource 
 */

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId("QuickQuestion_Resources");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
        http
            .requestMatchers().antMatchers("/oauth2/v2/questions/**")
            .and()
            .authorizeRequests().antMatchers("/oauth2/v2/questions/**").authenticated();
	}
	
}
