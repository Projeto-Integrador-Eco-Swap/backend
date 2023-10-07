package com.generation.ecoswap.security;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * A classe {@code BasicSecurityConfig} configura a segurança básica da aplicação, incluindo autenticação e autorização.
 * Também define as configurações para o filtro JWT e as restrições de acesso a URLs específicas.
 */
@Configuration
@EnableWebSecurity
public class BasicSecurityConfig {

    /**
     * Filtro JWT para autenticação de usuários.
     */
    private final JwtAuthFilter authFilter;

    /**
     * Construtor da classe {@code BasicSecurityConfig}.
     *
     * @param authFilter Filtro JWT para autenticação
     */
    @Contract(pure = true)
    @Autowired
    public BasicSecurityConfig(JwtAuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    /**
     * Cria um serviço de detalhes de usuário personalizado.
     *
     * @return O serviço de detalhes de usuário personalizado
     */
    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    /**
     * Cria um codificador de senha BCrypt para criptografar senhas.
     *
     * @return O codificador de senha BCrypt
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura o provedor de autenticação para autenticação de usuários.
     *
     * @return O provedor de autenticação
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Configura o gerenciador de autenticação da aplicação.
     *
     * @param authenticationConfiguration A configuração de autenticação
     * @return O gerenciador de autenticação
     * @throws Exception Se ocorrer um erro ao configurar o gerenciador de autenticação
     */
    @Bean
    AuthenticationManager authenticationManager(@NotNull AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configura a cadeia de filtros de segurança, incluindo o filtro JWT e as restrições de acesso a URLs.
     *
     * @param http O objeto de configuração de segurança HTTP
     * @return A cadeia de filtros de segurança configurada
     * @throws Exception Se ocorrer um erro ao configurar a cadeia de filtros de segurança
     */
    @Bean
    SecurityFilterChain filterChain(@NotNull HttpSecurity http) throws Exception {

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .cors();

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/usuarios/logar").permitAll()
                        .requestMatchers("/usuarios/cadastrar").permitAll()
                        .requestMatchers("/error/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic();

        return http.build();
    }
}