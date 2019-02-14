package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class Member {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true, length = 50)
	private String loginId;

	@Column(nullable = false, length = 200)
	@Convert(converter = PasswordConverter.class)
	private String password;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Enumerated(EnumType.STRING)
	private MemberRole role;

	public Member(String loginId, String password, MemberRole role) {
		super();
		this.loginId = loginId;
		this.password = password;
		this.role = role;
		this.creationDate = new Date();
	}

	Member() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public MemberRole getRole() {
		return role;
	}

	public void setRole(MemberRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", loginId=" + loginId + ", password=" + password + ", creationDate=" + creationDate
				+ ", role=" + role + "]";
	}

}
