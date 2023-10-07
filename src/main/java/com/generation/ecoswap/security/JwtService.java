package com.generation.ecoswap.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * A classe {@code JwtService} é responsável por realizar operações relacionadas a tokens JWT
 * (JSON Web Tokens), como criação, validação e extração de informações de tokens.
 * Ela utiliza uma chave secreta para assinar e verificar a autenticidade dos tokens.
 */
@Component
public class JwtService {

    /**
     * Chave secreta utilizada para assinar os tokens JWT.
     * Essa chave deve ser mantida em segredo e protegida adequadamente.
     */
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    /**
     * Retorna a chave de assinatura utilizada para criar e validar tokens JWT.
     *
     * @return A chave de assinatura
     */
    private @NotNull Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Extrai todas as informações (claims) de um token JWT.
     *
     * @param token O token JWT
     * @return Um objeto Claims contendo as informações do token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey()).build()
                .parseClaimsJws(token).getBody();
    }

    /**
     * Extrai uma claim específica de um token JWT.
     *
     * @param token         O token JWT
     * @param claimsResolver Uma função que resolve a claim desejada a partir das claims do token
     * @param <T>           O tipo de dado da claim
     * @return O valor da claim extraída
     */
    public <T> T extractClaim(String token, @NotNull Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrai o nome de usuário (subject) de um token JWT.
     *
     * @param token O token JWT
     * @return O nome de usuário extraído do token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrai a data de expiração de um token JWT.
     *
     * @param token O token JWT
     * @return A data de expiração do token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Verifica se um token JWT está expirado.
     *
     * @param token O token JWT
     * @return true se o token estiver expirado, false caso contrário
     */
    private @NotNull Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Valida a autenticidade de um token JWT em relação a um UserDetails (detalhes do usuário).
     *
     * @param token       O token JWT
     * @param userDetails Os detalhes do usuário obtidos do sistema de autenticação
     * @return true se o token for válido e correspondente ao usuário, false caso contrário
     */
    public Boolean validateToken(String token, @NotNull UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Cria um novo token JWT com base nas informações do usuário.
     *
     * @param claims   Um mapa de claims personalizadas a serem incluídas no token
     * @param userName O nome de usuário (subject) a ser incluído no token
     * @return O token JWT gerado
     */
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Gera um token JWT com base no nome de usuário.
     *
     * @param userName O nome de usuário para o qual o token será gerado
     * @return O token JWT gerado
     */
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }
}