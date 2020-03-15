package com.ownersite.rdr;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The configuration class extending {@link WebSecurityConfigurerAdapter}
 * 
 * @author basridha
 *
 */
@Configuration
@EnableWebSecurity
public class AuthTokenSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.antMatcher("*").csrf().disable().authorizeRequests().anyRequest().authenticated();
	}

}
