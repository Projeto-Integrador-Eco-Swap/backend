package com.generation.backend.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_Users")
public class Users {

//  Identificador único da Tabela
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//  Nome do usuário
	@NotNull(message = "O Atributo Nome é Obrigatório!")
	private String name;

//  Login do usuário
	@NotNull(message = "O Atributo Nome é Obrigatório!")
	@Email(message = "O Atributo Usuário deve ser um email válido!")
	private String user;

//  Senha do Usuário
	@NotBlank(message = "O Atributo Senha é Obrigatório!")
	@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
	private String password;

//  Foto do usuário
	@Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caracteres")
	private String picture;

//  GETTERS e SETTERS

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}
