package com.generation.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "tb_Users")

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name = "user_type")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	@NotNull
	private String name;

//	@ManyToOne
//	@JoinColumn(name = "mail_id")

	@NotNull
	private String mail;

	@Column(name = "password")
	@NotNull
	private String password;

//	@ManyToOne
//	@JoinColumn(name = "address_id")
//	@NotNull
//	private Address address;

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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
