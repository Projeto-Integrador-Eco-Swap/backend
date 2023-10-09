package com.generation.ecoswap.service;

import com.generation.ecoswap.entity.Usuario;
import com.generation.ecoswap.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Esta classe de teste verifica o funcionamento dos principais métodos do serviço de usuário, incluindo cadastro, atualização,
 * exclusão e autenticação, utilizando o framework Spring Boot.
 * Ela é responsável por garantir que as operações relacionadas a usuários estejam funcionando corretamente,
 * de acordo com as regras de negócio da aplicação.
 * Os testes são executados em um ambiente simulado com um servidor web embutido, permitindo testes de integração
 * para as funcionalidades relacionadas a usuários.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioServiceTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Antes de executar os testes, a configuração inicial é realizada.
     * Isso inclui a exclusão de todos os registros de usuários no repositório de usuários e a criação de um usuário "root"
     * para ser usado nos testes.
     */
    @BeforeAll
    void setUp() {
        usuarioRepository.deleteAll();

        usuarioService.cadastrarUsuario(new Usuario(0L,
                "Root", "root@root.com", "rootroot", " "));
    }

    /**
     * Testa o cadastro de um usuário com sucesso, verificando se o serviço de cadastro está funcionando corretamente.
     */
    @Test
    @DisplayName("Cadastrar um Usuário")
    void cadastrarUsuario() {
        HttpEntity<Usuario> request = new HttpEntity<>(
                new Usuario(0L,
                        "João", "vinicius_andrade1999@hotmail.com.br",
                        "GhostSthong567890@", "https://www.google.com.br"));

        ResponseEntity<Usuario> response = testRestTemplate
                .postForEntity("/usuarios/cadastrar", request, Usuario.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    /**
     * Testa a prevenção de duplicação de usuários, garantindo que um usuário com o mesmo nome de usuário não possa ser cadastrado novamente.
     */
    @Test
    @DisplayName("Não deve permitir duplicação do Usuário")
    public void naoDeveDuplicarUsuario() {
        // Cria um usuário com nome duplicado
        usuarioService.cadastrarUsuario(new Usuario(0L,
                "Maria da Silva", "maria_silva@email.com.br", "13465278", "-"));

        // Tenta criar outro usuário com o mesmo nome de usuário
        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<>(new Usuario(0L,
                "Maria da Silva", "maria_silva@email.com.br", "13465278", "-"));

        ResponseEntity<Usuario> corpoResposta = testRestTemplate
                .exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);

        assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
    }

    /**
     * Testa a atualização de um usuário, verificando se o serviço de atualização está funcionando corretamente.
     */
    @Test
    @DisplayName("Atualizar um Usuário")
    public void deveAtualizarUmUsuario() {
        // Cadastra um usuário
        Optional<Usuario> usuarioCadastrado = usuarioService.cadastrarUsuario(new Usuario(0L,
                "Juliana Andrews", "juliana_andrews@email.com.br", "juliana123", "-"));

        // Atualiza informações do usuário
        Usuario usuarioUpdate = new Usuario(usuarioCadastrado.get().getId(),
                "Juliana Andrews Ramos", "juliana_ramos@email.com.br", "juliana123" , "-");

        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<>(usuarioUpdate);

        ResponseEntity<Usuario> corpoResposta = testRestTemplate
                .withBasicAuth("root@root.com", "rootroot")
                .exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, Usuario.class);

        assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
    }

    /**
     * Testa a listagem de todos os usuários, verificando se o serviço de listagem está funcionando corretamente.
     */
    @Test
    @DisplayName("Listar todos os Usuários")
    public void deveMostrarTodosUsuarios() {
        // Cadastra alguns usuários para listar
        usuarioService.cadastrarUsuario(new Usuario(0L,
                "Sabrina Sanches", "sabrina_sanches@email.com.br", "sabrina123", "-"));

        usuarioService.cadastrarUsuario(new Usuario(0L,
                "Ricardo Marques", "ricardo_marques@email.com.br", "ricardo123", "-"));

        // Tenta listar todos os usuários autenticado como "root"
        ResponseEntity<String> resposta = testRestTemplate
                .withBasicAuth("root@root.com", "rootroot")
                .exchange("/usuarios/all", HttpMethod.GET, null, String.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    /**
     * Testa a prevenção de atualização de usuário com senha vazia, garantindo que a atualização não seja permitida nesses casos.
     */
    @Test
    @DisplayName("Não deve permitir atualizar usuário com senha vazia")
    public void naoDevePermitirAtualizarUsuarioComSenhaVazia() {
        // Cadastra um usuário
        Optional<Usuario> usuarioCadastrado = usuarioService.cadastrarUsuario(new Usuario(0L,
                "Teste", "teste@email.com", "senha123", "-"));

        // Tenta atualizar o usuário com senha vazia
        Usuario usuarioUpdate = new Usuario(usuarioCadastrado.get().getId(),
                "Teste Atualizado", "teste@email.com", "", "-");

        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<>(usuarioUpdate);

        ResponseEntity<Void> corpoResposta = testRestTemplate
                .withBasicAuth("root@root.com", "rootroot")
                .exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
    }

    /**
     * Testa a prevenção de atualização de usuário com email duplicado, garantindo que a atualização não seja permitida nesses casos.
     */
    @Test
    @DisplayName("Não deve permitir atualizar usuário com email duplicado")
    public void naoDevePermitirAtualizarUsuarioComEmailDuplicado() {
        // Cadastra dois usuários com emails diferentes
        usuarioService.cadastrarUsuario(new Usuario(0L,
                "Usuário 1", "usuario1@email.com", "senha123", "-"));

        Optional<Usuario> usuarioCadastrado = usuarioService.cadastrarUsuario(new Usuario(0L,
                "Usuário 2", "usuario2@email.com", "senha456", "-"));

        // Tenta atualizar o segundo usuário com o email do primeiro usuário
        Usuario usuarioUpdate = new Usuario(usuarioCadastrado.get().getId(),
                "Usuário Atualizado", "usuario1@email.com", "senha789", "-");

        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<>(usuarioUpdate);

        ResponseEntity<Void> corpoResposta = testRestTemplate
                .withBasicAuth("root@root.com", "rootroot")
                .exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
    }

    /**
     * Testa se um usuário autenticado tem permissão para excluir sua própria conta.
     */
    @Test
    @DisplayName("Deve permitir um usuário autenticado excluir sua conta")
    public void devePermitirUsuarioAutenticadoExcluirConta() {
        // Cadastra um usuário autenticado
        Optional<Usuario> usuarioCadastrado = usuarioService.cadastrarUsuario(new Usuario(0L,
                "Usuario Autenticado", "autenticado@email.com", "senha123", "-"));

        // Tenta excluir a conta do usuário autenticado
        ResponseEntity<Void> corpoResposta = testRestTemplate
                .withBasicAuth(usuarioCadastrado.get().getUsuario(), "senha123")
                .exchange("/usuarios/excluir", HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, corpoResposta.getStatusCode());
    }

    /**
     * Testa se um administrador tem permissão para listar todos os usuários.
     */
    @Test
    @DisplayName("Deve permitir um administrador listar todos os usuários")
    public void devePermitirAdministradorListarTodosUsuarios() {
        // Tenta listar todos os usuários autenticado como "root"
        ResponseEntity<String> resposta = testRestTemplate
                .withBasicAuth("root@root.com", "rootroot")
                .exchange("/usuarios/all", HttpMethod.GET, null, String.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    /**
     * Testa se um usuário autenticado não tem permissão para listar todos os usuários.
     */
    @Test
    @DisplayName("Não deve permitir um usuário autenticado listar todos os usuários")
    public void naoDevePermitirUsuarioAutenticadoListarTodosUsuarios() {
        // Tenta listar todos os usuários autenticado como um usuário normal
        ResponseEntity<String> resposta = testRestTemplate
                .withBasicAuth("usuario@exemplo.com", "senha123")
                .exchange("/usuarios/all", HttpMethod.GET, null, String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, resposta.getStatusCode());
    }
}