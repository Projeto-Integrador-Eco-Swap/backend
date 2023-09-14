package com.generation.backend.service;

import com.generation.backend.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Interface que define os serviços relacionados a usuários.
 */
public interface UserService {

    /**
     * Obtém uma lista de todos os usuários.
     *
     * @return Uma lista contendo todos os usuários no sistema.
     */
    List<User> getAllUsers();

    /**
     * Cria um novo usuário.
     *
     * @param user O usuário a ser criado.
     * @return O usuário criado.
     */
    User createUser(User user);

    /**
     * Obtém um usuário pelo seu identificador único (ID).
     *
     * @param id O ID do usuário a ser obtido.
     * @return O usuário com o ID especificado, ou null se não encontrado.
     */
    User getUserById(Long id);

    /**
     * Obtém um usuário pelo seu nome.
     *
     * @param name O nome do usuário a ser obtido.
     * @return O usuário com o nome especificado, ou null se não encontrado.
     */
    User getUserByName(String name);

    /**
     * Atualiza um usuário existente.
     *
     * @param user O usuário atualizado.
     * @return O usuário atualizado.
     */
    User updateUser(User user);

    /**
     * Atualiza o nome de um usuário existente.
     *
     * @param user O usuário atualizado.
     * @return O usuário atualizado.
     */
    User updateUserBirthDate(User user);

    /**
     * Exclui um usuário pelo seu identificador único (ID).
     *
     * @param id O ID do usuário a ser excluído.
     * @return Um mapa contendo uma mensagem de status da exclusão.
     */
    Map<String, String> deleteUserById(Long id);

    /**
     * Exclui um usuário pelo seu nome.
     *
     * @param name O nome do usuário a ser excluído.
     * @return Um mapa contendo uma mensagem de status da exclusão.
     */
    Map<String, String> deleteUserByName(String name);


    /**
     * Atualiza a senha de um usuário existente.
     *
     * @param user O usuário atualizado.
     * @return O usuário atualizado.
     */
    User updateUserPassword(User user);


    /**
     * Obtém um usuário pelo seu nome e senha.
     *
     * @param name     O nome do usuário a ser obtido.
     * @param password A senha do usuário a ser obtido.
     * @return O usuário com o nome e senha especificados, ou null se não encontrado.
     */
    User getUserByNameAndPassword(String name, String password);

    /**
     * Procura um usuário pelo nome e lança uma exceção se não for encontrado.
     *
     * @param name O nome do usuário a ser encontrado.
     * @return O usuário encontrado.
     * @throws IllegalArgumentException Se o usuário não for encontrado.
     */
    User getUserByNameAndEmail(String name, String email);


    /**
     * Obtém um usuário pelo sua  data de nascimento.
     *
     * @param birthDate A data de nascimento do usuário a ser obtido.
     * @return O usuário com a data de nascimento especificada, ou null se não encontrado.
     */
    User getUserByBirthDay(String birthDate);

    /**
     * Exclui um usuário pelo seu nome e senha.
     *
     * @param name     O nome do usuário a ser excluído.
     * @param password A senha do usuário a ser excluído.
     * @return Um ResponseEntity vazio (sem corpo) indicando sucesso.
     */
    void deleteUserByNameAndPassword(String name, String password);

    /**
     * Exclui um usuário pelo seu nome e email.
     *
     * @param name  O nome do usuário a ser excluído.
     * @param email O email do usuário a ser excluído.
     * @return Um ResponseEntity vazio (sem corpo) indicando sucesso.
     */
    void deleteUserByNameAndEmail(String name, String email);

    /**
     * Exclui um usuário pela sua data de nascimento.
     *
     * @param birthDate A data de nascimento do usuário a ser excluído.
     * @return Um ResponseEntity vazio (sem corpo) indicando sucesso.
     */

    void deleteUserByBirthDay(String birthDate);

    /**
     * Exclui todos os usuários.
     */
    void deleteAllUsers();
}