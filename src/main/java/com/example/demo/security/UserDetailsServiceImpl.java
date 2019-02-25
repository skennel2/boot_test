package com.example.demo.security;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Member;
import com.example.demo.domain.SecurityMember;
import com.example.demo.repository.MemberRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {		
		Member member = memberRepository.findByLoginId(loginId);
		
		if(Objects.isNull(member)) {
			throw new UsernameNotFoundException(loginId);
		}
		
		return new SecurityMember(member);
	}

}
