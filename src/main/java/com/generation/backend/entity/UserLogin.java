package com.generation.backend.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserLogin {

	private Long id;
	private String firstName;
	private String lastName;
	private String user;
	private String password;
	private String picture;
	private String token;
	
}
