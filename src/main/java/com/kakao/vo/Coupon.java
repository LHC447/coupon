package com.kakao.vo;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
public class Coupon {
	@Id
	@GeneratedValue
	@JsonProperty
	private Long id;
	
	@Column(nullable = false, unique = true)
	@JsonProperty
	private String email;
	
	@JsonProperty
	private String code;
	
	
	private LocalDateTime createDate;
	
	
	public Coupon() {}
	
	public Coupon(String email, String code) {
		super();
		this.email = email;
		this.code = code;
		this.createDate = LocalDateTime.now();
	}
	
	
	public String getFormattedCreateDate() {
		if (createDate==null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	
}
