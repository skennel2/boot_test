package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@RequestMapping(path = "/join", method = RequestMethod.POST)
	public ResponseEntity<Void> joinUs(@RequestParam String name, @RequestParam String password) {
		Member member = new Member(name, password, MemberRole.Normal);
		memberRepo.save(member);
		
		System.out.println(member.toString());
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(path = "/join", method = RequestMethod.GET)
	public String joinUs() {
		return "join";
	}
}
