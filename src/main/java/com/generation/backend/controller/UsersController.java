package com.generation.backend.controller;

import com.generation.backend.model.Users;
import com.generation.backend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Obtém todos os usuários cadastrados no sistema.
     *
     * @return Uma lista de todos os usuários.
     */
    @GetMapping("/all")
    public ResponseEntity<Iterable<Users>> getAllUsers() {
        Iterable<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Obtém um usuário pelo seu ID.
     *
     * @param id O ID do usuário a ser obtido.
     * @return O usuário com o ID especificado, ou nulo se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Users user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Atualiza as informações de um usuário existente.
     *
     * @param user O usuário atualizado.
     * @return O usuário atualizado.
     */
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<Users> updateUser(@RequestBody Users user) {
        Users updatedUser = userService.updateUser(user);
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
}