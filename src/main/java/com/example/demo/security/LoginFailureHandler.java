package com.example.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/*
 * https://www.baeldung.com/spring-security-custom-authentication-failure-handler
 * 
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		request.setAttribute("failure_message", "아이디나 비밀번호를 확인해주세요.");
		request.setAttribute("before_id", request.getParameter("loginId")); // TODO ? request에서 parameter랑 attribute의 차이는?
		request.getRequestDispatcher("/member/login?error=true").forward(request, response);
	}

}
