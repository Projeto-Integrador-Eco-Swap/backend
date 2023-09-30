package com.generation.backend.service;

import com.generation.backend.entity.User;
import com.generation.backend.entity.UserLogin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * Interface que define os serviços relacionados a usuários.
 */
public interface UserService {

    Optional<UserLogin> autenticarUsuario(@NotNull Optional<UserLogin> usuarioLogin);

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
     * Obtém um usuário pelo seu firstName.
     *
     * @param firstName O nome do usuário a ser obtido.
     * @return O usuário com o nome especificado, ou null se não encontrado.
     */
    User getUserByName(String firstName);

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
     */
    void deleteUserById(Long id);

    /**
     * Exclui um usuário pelo seu firstName.
     *
     * @param firstName O nome do usuário a ser excluído.
     */
    void deleteUserByName(String firstName);

    /**
     * Atualiza a senha de um usuário existente.
     *
     * @param user O usuário atualizado.
     * @return O usuário atualizado.
     */
    User updateUserPassword(User user);

    /**
     * Atualiza o email de um usuário existente.
     *
     * @param user O usuário atualizado.
     * @return O usuário atualizado.
     */
    User updateUserEmail(User user);

    /**
     * Obtém um usuário pelo sua  data de nascimento.
     *
     * @param birthDate A data de nascimento do usuário a ser obtido.
     * @return O usuário com a data de nascimento especificada, ou null se não encontrado.
     */
    User getUserByBirthDay(String birthDate);

    /**
     * Exclui um usuário pelo sua data de nascimento.
     *
     * @param birthDate A data de nascimento do usuário a ser excluído.
     */
    void deleteUserByBirthDay(String birthDate);

    /**
     * Exclui todos os usuários.
     */
    void deleteAllUsers();

    Optional<User> cadastrarUsuario(@NotNull Optional<UserLogin> user);

    Optional<User> atualizarUsuario(@NotNull Optional<UserLogin> user);

}