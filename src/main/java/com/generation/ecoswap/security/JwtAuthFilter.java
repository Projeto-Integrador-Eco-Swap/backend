package com.generation.ecoswap.security;

import java.io.IOException;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A classe {@code JwtAuthFilter} é responsável por filtrar e validar tokens JWT nas requisições HTTP.
 * Ela verifica se um token JWT válido está presente no cabeçalho "Authorization" da requisição,
 * e, se estiver, autentica o usuário com base nas informações do token.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    /**
     * Serviço para operações relacionadas a tokens JWT.
     */
    private final JwtService jwtService;
    /**
     * Serviço para carregar detalhes do usuário.
     */
    private final UserDetailsServiceImpl userDetailsService;

    /**
     * Construtor da classe {@code JwtAuthFilter}.
     *
     * @param jwtService       Serviço para operações relacionadas a tokens JWT
     * @param userDetailsService Serviço para carregar detalhes do usuário
     */
    @Autowired
    public JwtAuthFilter(
            JwtService jwtService,
            UserDetailsServiceImpl userDetailsService
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Método para realizar o filtro e validação do token JWT.
     *
     * @param request     A requisição HTTP
     * @param response    A resposta HTTP
     * @param filterChain A cadeia de filtros
     * @throws ServletException Se ocorrer um erro no servlet
     * @throws IOException      Se ocorrer um erro de E/S
     */
    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = jwtService.extractUsername(token);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            }
            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
                 | SignatureException | ResponseStatusException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }
}