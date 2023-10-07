package com.generation.ecoswap.repository;

import com.generation.ecoswap.entity.Usuario;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Testes para o {@link UsuarioRepository}.
 */
@SpringBootTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Teste para cadastrar um usuário.
     */
    @Test
    @Transactional
    @DisplayName("Cadastrar Usuário")
    void cadastrarUsuario() {

        Usuario usuario = new Usuario();
        usuario.setNome("João");
        usuario.setUsuario("vinicius_andrade2010@hotmail.com");
        usuario.setSenha("GhostSthong567890@");
        usuario.setFoto("https://www.google.com.br");
        usuarioRepository.save(usuario);
        System.out.println(usuario);
    }

    /**
     * Teste para consultar um usuário pelo ID.
     */
    @Test
    @DisplayName("Consultar Usuário")
    void consultarUsuario() {
        Usuario usuario = usuarioRepository.findById(1L).get();
        System.out.println(usuario);
    }

    /**
     * Teste para consultar todos os usuários.
     */
    @Test
    @DisplayName("Consultar Todos os Usuários")
    void consultaTodosUsuarios() {
        for (Usuario usuario : usuarioRepository.findAll()) {
            System.out.println(usuario);
        }
    }

    /**
     * Teste para consultar usuários pelo nome.
     */
    @Test
    @DisplayName("Consultar Usuário por Nome")
    void consultarUsuarioPorNome() {
        Usuario usuario = usuarioRepository.findByNome("João").get(0);
        System.out.println(usuario);
    }

    /**
     * Teste para atualizar um usuário.
     */
    @Test
    @DisplayName("Atualizar Usuário")
    @Transactional
    void atualizarUsuario() {
        Usuario usuario = usuarioRepository.findById(1L).get();
        usuario.setNome("João da Silva");
        usuarioRepository.save(usuario);
        System.out.println(usuario);
    }

    /**
     * Teste para deletar um usuário.
     */
    @Test
    @DisplayName("Deletar Usuário")
    @Transactional
    void deletarUsuario() {
        Usuario usuario = usuarioRepository.findById(1L).get();
        usuarioRepository.delete(usuario);
        System.out.println(usuario);
    }
}
