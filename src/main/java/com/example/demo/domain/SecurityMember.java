package com.example.demo.domain;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecurityMember extends User {
	private static final long serialVersionUID = 3968631068691248827L;

	public SecurityMember(Member member) {
		super(member.getLoginId(), member.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority(member.getRole().name())));
	}

}
