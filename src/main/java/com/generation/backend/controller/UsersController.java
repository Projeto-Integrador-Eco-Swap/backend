package com.generation.backend.controller;

import com.generation.backend.entity.User;
import com.generation.backend.entity.UserLogin;
import com.generation.backend.service.UserService;
import com.generation.backend.service.implementation.UserServiceImpl;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Controlador responsável por gerenciar endpoints relacionados aos usuários.
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsersController {

    /**
     * O serviço para usuários.
     */
    private final UserService userService;
    private final UserServiceImpl usuarioServiceImpl;

    /**
     * Construtor que injeta o serviço de usuários.
     *
     * @param userService O serviço de usuários.
     */
    @Contract(pure = true)
    @Autowired
    public UsersController(UserService userService, UserServiceImpl usuarioServiceImpl) {
        this.userService = userService;
        this.usuarioServiceImpl = usuarioServiceImpl;
    }

    /**
     * Cria um novo usuário no sistema.
     *
     * @param user O usuário a ser criado.
     * @return O usuário criado.
     */
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Realiza a autenticação do usuário com as informações fornecidas.
     *
     * @param userLogin As informações de login do usuário.
     * @return Um ResponseEntity contendo os detalhes do usuário autenticado com código de status HTTP correspondente.
     */
    @PostMapping("/login")
    public ResponseEntity<UserLogin> autenticarUsuario(@RequestBody UserLogin userLogin) {
        Optional<UserLogin> resposta = usuarioServiceImpl.autenticarUsuario(Optional.ofNullable(userLogin));
        return resposta.map(usuario -> ResponseEntity.status(HttpStatus.OK).body(usuario))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    /**
     * Cadastra um novo usuário com as informações fornecidas.
     *
     * @param userLogin As informações do usuário a serem cadastradas.
     * @return Um ResponseEntity contendo os detalhes do usuário cadastrado com código de status HTTP correspondente.
     */
    @PostMapping("/cadastrar")
    public ResponseEntity<User> cadastrarUsuario(@RequestBody UserLogin userLogin) {
        Optional<User> resposta = usuarioServiceImpl.cadastrarUsuario(Optional.ofNullable(userLogin));
        return resposta.map(usuario -> ResponseEntity.status(HttpStatus.CREATED).body(usuario))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    /**
     * Atualiza as informações de um usuário com base nos dados fornecidos.
     *
     * @param userLogin As informações de usuário a serem atualizadas.
     * @return Um ResponseEntity contendo os detalhes do usuário atualizado com código de status HTTP correspondente.
     */
    @PutMapping("/atualizar")
    public ResponseEntity<User> atualizarUsuario(@RequestBody UserLogin userLogin) {
        Optional<User> resposta = usuarioServiceImpl.atualizarUsuario(Optional.ofNullable(userLogin));
        return resposta.map(usuario -> ResponseEntity.status(HttpStatus.OK).body(usuario))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    /**
     * Obtém todos os usuários cadastrados no sistema.
     *
     * @return Uma lista de todos os usuários.
     */
    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Obtém um usuário pelo seu ID.
     *
     * @param id O ID do usuário a ser obtido.
     * @return O usuário com o ID especificado, ou nulo se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Obtém um usuário pelo seu nome.
     *
     * @param firstName O nome do usuário a ser obtido.
     * @return O usuário com o nome especificado, ou null se não encontrado.
     */
    @GetMapping("/name/{firstName}")
    public ResponseEntity<User> getUserByName(@PathVariable String firstName) {
        User user = userService.getUserByName(firstName);
        return ResponseEntity.ok(user);
    }

    /**
     * Obtém um usuário pela sua data de nascimento.
     *
     * @param birthDate A data de nascimento do usuário a ser obtido.
     * @return O usuário com a data de nascimento especificada, ou null se não encontrado.
     */
    @GetMapping("/birthdate/{birthDate}")
    public ResponseEntity<User> getUserByBirthDay(@PathVariable String birthDate) {
        User user = userService.getUserByBirthDay(birthDate);
        return ResponseEntity.ok(user);
    }

    /**
     * Atualiza o nome de um usuário existente.
     *
     * @param user O usuário atualizado.
     * @return O usuário atualizado.
     */
    @PutMapping("/update/birthdate")
    @Transactional
    public ResponseEntity<User> updateUserBirthDate(@RequestBody User user) {
        User updatedUser = userService.updateUserBirthDate(user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Atualiza a senha de um usuário existente.
     *
     * @param user O usuário atualizado.
     * @return O usuário atualizado.
     */
    @PutMapping("/update/password")
    @Transactional
    public ResponseEntity<User> updateUserPassword(@RequestBody @NotNull User user) {
        User updatedUser = userService.updateUserPassword(user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Atualiza o endereço de e-mail de um usuário existente.
     * <p>
     * Este endpoint permite a atualização do endereço de e-mail de um usuário existente
     * com base no ID fornecido. O usuário atualizado é retornado como resposta.
     *
     * @param user O usuário com as informações atualizadas.
     * @return O usuário atualizado com o novo endereço de e-mail.
     */
    @PostMapping("/users/updateEmail")
    @Transactional
    public ResponseEntity<User> updateUserEmail(@RequestBody @NotNull User user) {
        User existingUser = userService.updateUserEmail(user);
        return ResponseEntity.ok(existingUser);
    }

    /**
     * Exclui um usuário pelo seu ID.
     *
     * @param id O ID do usuário a ser excluído.
     * @return Um ResponseEntity vazio (sem corpo) indicando sucesso.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Exclui um usuário pelo seu nome.
     *
     * @param firstName O nome do usuário a ser excluído.
     * @return Um ResponseEntity vazio (sem corpo) indicando sucesso.
     */
    @DeleteMapping("/delete/name/{firstName}")
    public ResponseEntity<Void> deleteUserByName(@PathVariable String firstName) {
        userService.deleteUserByName(firstName);
        return ResponseEntity.ok().build();
    }

    /**
     * Exclui um usuário pela sua data de nascimento.
     *
     * @param birthDate A data de nascimento do usuário a ser excluído.
     * @return Um ResponseEntity vazio (sem corpo) indicando sucesso.
     */
    @DeleteMapping("/delete/birthdate/{birthDate}")
    public ResponseEntity<Void> deleteUserByBirthDay(@PathVariable String birthDate) {
        userService.deleteUserByBirthDay(birthDate);
        return ResponseEntity.ok().build();
    }

    /**
     * Exclui todos os usuários.
     *
     * @return Um ResponseEntity vazio (sem corpo) indicando sucesso.
     */
    @DeleteMapping("/delete/all")
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.ok().build();
    }
}