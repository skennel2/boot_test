package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.example.demo.domain.MemberRole;
import com.example.demo.security.LoginFailureHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {				
				return rawPassword.toString().equals(encodedPassword);
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}
		});	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http
			.authorizeRequests()
				.antMatchers("/admin/**").hasRole(MemberRole.Admin.name())
				.antMatchers("/index/**").authenticated()
				.antMatchers("/student/**").authenticated()
				.antMatchers("/member/login").permitAll()
				.antMatchers("/member/join").permitAll()
				.and()
			.formLogin()
				.loginPage("/member/login")
				.loginProcessingUrl("/security/login")
				.defaultSuccessUrl("/member/login/success")			
				.failureHandler(authFailureHander())
				.and()
			.csrf()
				.and()
			.logout()
				.logoutUrl("/security/logout");
	}
	
	@Bean
	AuthenticationFailureHandler authFailureHander() {
		return new LoginFailureHandler();
	}
}
