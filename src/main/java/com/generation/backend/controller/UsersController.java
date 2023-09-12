package com.generation.backend.controller;

import com.generation.backend.entity.User;
import com.generation.backend.service.UserService;
import jakarta.transaction.Transactional;
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

    /**
     * Cria um novo controlador de usuários.
     *
     * @param userService O serviço para usuários.
     */
    public UsersController(UserService userService) {
        this.userService = userService;
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
     * @param name O nome do usuário a ser obtido.
     * @return O usuário com o nome especificado, ou null se não encontrado.
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        User user = userService.getUserByName(name);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/birthdate")
    @Transactional
    public ResponseEntity<User> updateUserBirthDate(@RequestBody User user) {
        User updatedUser = userService.updateUserBirthDate(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/update/password")
    @Transactional
    public ResponseEntity<User> updateUserPassword(@RequestBody User user) {
        User updatedUser = userService.updateUserPassword(user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Atualiza as informações de um usuário existente.
     *
     * @param user O usuário atualizado.
     * @return O usuário atualizado.
     */
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
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
     * @param name O nome do usuário a ser excluído.
     * @return Um ResponseEntity vazio (sem corpo) indicando sucesso.
     */
    @DeleteMapping("/delete/name/{name}")
    public ResponseEntity<Void> deleteUserByName(@PathVariable String name) {
        userService.deleteUserByName(name);
        return ResponseEntity.ok().build();
    }
}