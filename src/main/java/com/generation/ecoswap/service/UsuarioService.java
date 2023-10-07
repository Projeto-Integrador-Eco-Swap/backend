package com.generation.ecoswap.service;

import java.util.Optional;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.ecoswap.entity.Usuario;
import com.generation.ecoswap.entity.UsuarioLogin;
import com.generation.ecoswap.repository.UsuarioRepository;
import com.generation.ecoswap.security.JwtService;

/**
 * A classe {@code UsuarioService} fornece serviços relacionados a usuários, como cadastro, autenticação e atualização de informações.
 */
@Service
public class UsuarioService {

    /**
     * Repositório para gerenciamento de entidades de usuário.
     */
    private final UsuarioRepository usuarioRepository;
    /**
     * Serviço para geração e validação de tokens JWT.
     */
    private final JwtService jwtService;
    /**
     * Gerenciador de autenticação Spring Security.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Construtor da classe {@code UsuarioService}.
     *
     * @param usuarioRepository Repositório de usuários
     * @param jwtService Serviço JWT
     * @param authenticationManager Gerenciador de autenticação
     */
    @Autowired
    @Contract(pure = true)
    public UsuarioService(
            UsuarioRepository usuarioRepository,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Cadastra um novo usuário.
     *
     * @param usuario O objeto de usuário a ser cadastrado
     * @return Um objeto Optional contendo o usuário cadastrado, ou vazio se o usuário já existir
     */
    public Optional<Usuario> cadastrarUsuario(@NotNull Usuario usuario) {

        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            return Optional.empty();

        usuario.setSenha(criptografarSenha(usuario.getSenha()));

        return Optional.of(usuarioRepository.save(usuario));
    }

    /**
     * Atualiza as informações de um usuário.
     *
     * @param usuario O objeto de usuário com informações atualizadas
     * @return Um objeto Optional contendo o usuário atualizado, ou vazio se o usuário não existir
     */
    public Optional<Usuario> atualizarUsuario(@NotNull Usuario usuario) {

        if (usuarioRepository.findById(usuario.getId()).isPresent()) {
            Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
            if ((buscaUsuario.isPresent()) && (!buscaUsuario.get().getId().equals(usuario.getId())))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

            usuario.setNome(usuario.getNome());
            usuario.setUsuario(usuario.getUsuario());
            usuario.setSenha(criptografarSenha(usuario.getSenha()));
            usuario.setFoto(usuario.getFoto());

            return Optional.of(usuarioRepository.save(usuario));
        }
        return Optional.empty();
    }

    /**
     * Atualiza o email de um usuário.
     *
     * @param usuario O objeto de usuário com o novo email
     * @return Um objeto Optional contendo o usuário com o email atualizado, ou vazio se o usuário não existir
     */
    public Optional<Usuario> atualizarEmailUsuario(@NotNull Usuario usuario) {

        if (usuarioRepository.findById(usuario.getId()).isPresent()) {
            Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
            if ((buscaUsuario.isPresent()) && (!buscaUsuario.get().getId().equals(usuario.getId()))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!");
            }
            usuario.setUsuario(usuario.getUsuario());
            return Optional.of(usuarioRepository.save(usuario));
        }
        return Optional.empty();
    }

    /**
     * Atualiza a senha de um usuário.
     *
     * @param usuario O objeto de usuário com a nova senha
     * @return Um objeto Optional contendo o usuário com a senha atualizada, ou vazio se o usuário não existir
     */
    public Optional<Usuario> atualizarSenhaUsuario(@NotNull Usuario usuario) {

        if (usuarioRepository.findById(usuario.getId()).isPresent()) {
            Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
            if ((buscaUsuario.isPresent()) && (!buscaUsuario.get().getId().equals(usuario.getId()))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!");
            }

            usuario.setSenha(criptografarSenha(usuario.getSenha()));
            return Optional.of(usuarioRepository.save(usuario));
        }
        return Optional.empty();
    }

    /**
     * Autentica um usuário com as credenciais fornecidas.
     *
     * @param usuarioLogin As credenciais de login do usuário
     * @return Um objeto Optional contendo o usuário autenticado, ou vazio se a autenticação falhar
     */
    public Optional<UsuarioLogin> autenticarUsuario(@NotNull Optional<UsuarioLogin> usuarioLogin) {

        // Gera o Objeto de autenticação
        var credenciais = new UsernamePasswordAuthenticationToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha());

        // Autentica o Usuario
        Authentication authentication = authenticationManager.authenticate(credenciais);

        // Se a autenticação foi efetuada com sucesso
        if (authentication.isAuthenticated()) {

            // Busca os dados do usuário
            Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

            // Se o usuário foi encontrado
            if (usuario.isPresent()) {

                // Preenche o Objeto usuarioLogin com os dados encontrados
                usuarioLogin.get().setId(usuario.get().getId());
                usuarioLogin.get().setNome(usuario.get().getNome());
                usuarioLogin.get().setFoto(usuario.get().getFoto());
                usuarioLogin.get().setToken(gerarToken(usuarioLogin.get().getUsuario()));
                usuarioLogin.get().setSenha("");

                // Retorna o Objeto preenchido
                return usuarioLogin;
            }
        }
        return Optional.empty();
    }

    /**
     * Criptografa uma senha usando o algoritmo BCrypt.
     *
     * @param senha A senha a ser criptografada
     * @return A senha criptografada
     */
    private String criptografarSenha(String senha) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(senha);
    }

    /**
     * Gera um token JWT para um usuário.
     *
     * @param usuario O nome de usuário para o qual o token será gerado
     * @return O token JWT gerado
     */
    private @NotNull String gerarToken(String usuario) {
        return "Bearer " + jwtService.generateToken(usuario);
    }
}