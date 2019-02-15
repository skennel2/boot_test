package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberRole;
import com.example.demo.repository.MemberRepository;

@Controller
@RequestMapping(path = "/member")
public class MemberController {

	@Autowired
	MemberRepository memberRepo;

	private final String BASE_URL = "/member";

	@RequestMapping(path = "/join", method = RequestMethod.POST)
	public String joinUs(@RequestParam String loginId, @RequestParam String password) {
		Member member = new Member(loginId, password, MemberRole.Normal);
		memberRepo.save(member);

		return BASE_URL + "/login/login";
	}

	@RequestMapping(path = "/join", method = RequestMethod.GET)
	public String joinUs() {
		return BASE_URL + "/join/join";
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return BASE_URL + "/login/login";
	}

	@RequestMapping(path = "/login/success", method = RequestMethod.GET)
	public String loginSuccess() {
		return BASE_URL + "/login/success";
	}

	@RequestMapping(path = "/login/fail", method = RequestMethod.GET)
	public String loginFail() {
		return BASE_URL + "/login/fail";
	}
}
