package com.generation.backend.security;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.backend.entity.User;


public class UserDetailsImpl implements UserDetails {
	@Serial
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserDetailsImpl(@NotNull User user) {
		this.userName = user.getFirstName();
		this.password = user.getPassword();
	}

	@Contract(pure = true)
	public UserDetailsImpl() {	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
