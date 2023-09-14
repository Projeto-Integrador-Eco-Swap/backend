package com.generation.backend.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin {

	private Long id;
	private String name;
	private String user;
	private String password;
	private String picture;
	private String token;

	
}
