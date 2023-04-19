package com.monocept.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	
	@Bean
	public InMemoryUserDetailsManager UserDetailsManager() {
		UserDetails David = User.builder().username("xyz").password("{noop}xyz").roles("staff").build();
		UserDetails Manish = User.builder().username("manish").password("{noop}manish").roles("admin").build();

		return new InMemoryUserDetailsManager(David,Manish);
	}
	
	@Bean
	public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((configurer) -> 
			
		configurer.requestMatchers(HttpMethod.GET,"/admin/users").hasRole("admin").
		requestMatchers(HttpMethod.GET,"/admin/users/**").hasRole("admin").
		requestMatchers(HttpMethod.POST,"/admin/users").hasRole("admin").
		requestMatchers(HttpMethod.PUT,"/admin/users/**").hasRole("admin").
		requestMatchers(HttpMethod.DELETE,"/admin/users/**").hasRole("admin").
		
		requestMatchers(HttpMethod.GET,"/staff/contacts/**").hasRole("staff").
		requestMatchers(HttpMethod.POST,"/staff/contacts/**").hasRole("staff").
		requestMatchers(HttpMethod.PUT,"/staff/contacts/**").hasRole("staff").
		requestMatchers(HttpMethod.DELETE,"/staff/contacts/**").hasRole("staff").
		
		requestMatchers(HttpMethod.GET,"/staff/contact/details/**").hasRole("staff").
		requestMatchers(HttpMethod.POST,"/staff/contact/details/**").hasRole("staff").
		requestMatchers(HttpMethod.PUT,"/staff/contact/details/**").hasRole("staff").
		requestMatchers(HttpMethod.DELETE,"/staff/contact/details/**").hasRole("staff")
		);
		
		http.httpBasic();
		http.csrf().disable();
		
		return http.build();
	}
}
