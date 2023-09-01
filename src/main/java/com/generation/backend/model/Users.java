package com.generation.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "tb_Users")
@Getter
@Setter

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "user_type")
public abstract class Users {
	
	@Id
	@Column(name = "user_id")
	private long id;

	@Column(name = "name")
	@NotNull
	private String name;

	@ManyToOne
	@JoinColumn(name = "mail_id")
	@NotNull
	private String mail;

	@Column(name = "password")
	@NotNull
	private String password;

	@ManyToOne
	@JoinColumn(name = "address_id")
	@NotNull
	private Address address;

}
