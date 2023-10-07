package com.generation.ecoswap.security;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

import com.generation.ecoswap.entity.Usuario;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * A classe {@code UserDetailsImpl} implementa a interface {@link UserDetails} do Spring Security
 * e representa os detalhes de autenticação do usuário. Ela é usada para encapsular informações
 * sobre um usuário, como nome de usuário e senha, para fins de autenticação e autorização.
 */
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    /**
     * Número de série para serialização da classe.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Construtor que cria um objeto UserDetailsImpl com base em um objeto de entidade de usuário.
     *
     * @param user O objeto de entidade de usuário a partir do qual os detalhes do usuário são extraídos.
     */
    private String userName;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserDetailsImpl(@NotNull Usuario user) {
        this.userName = user.getUsuario();
        this.password = user.getSenha();
    }

    /**
     * Retorna a coleção de autorizações (ou papéis) concedidos a este usuário.
     *
     * @return A coleção de autorizações deste usuário.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Retorna a senha associada a este usuário.
     *
     * @return A senha deste usuário.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Retorna o nome de usuário associado a este usuário.
     *
     * @return O nome de usuário deste usuário.
     */
    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * Indica se a conta deste usuário está expirada.
     *
     * @return {@code true} se a conta não estiver expirada, caso contrário, {@code false}.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica se a conta deste usuário está bloqueada.
     *
     * @return {@code true} se a conta não estiver bloqueada, caso contrário, {@code false}.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica se as credenciais deste usuário estão expiradas.
     *
     * @return {@code true} se as credenciais não estiverem expiradas, caso contrário, {@code false}.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica se este usuário está habilitado.
     *
     * @return {@code true} se o usuário estiver habilitado, caso contrário, {@code false}.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}