package com.generation.backend.service.implementation;

import com.generation.backend.model.Users;
import com.generation.backend.repository.UsersRepository;
import com.generation.backend.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A implementação do serviço de usuário.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * O repositório de usuários.
     */
    private final UsersRepository usersRepository;

    /**
     * Cria um novo serviço de usuário.
     *
     * @param usersRepository O repositório de usuários.
     */
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * Obtém todos os usuários cadastrados no sistema.
     *
     * @return Uma lista de todos os usuários.
     */
    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    /**
     * Cria um novo usuário no sistema.
     *
     * @param user O usuário a ser criado.
     * @return O usuário criado.
     * @throws IllegalArgumentException Se o usuário já possui um ID.
     */
    @Override
    public Users createUser(Users user) {
        validateUserForCreation(user);

        if (user.getId() != null) {
            throw new IllegalArgumentException("O usuário a ser criado não deve ter um ID.");
        }

        return usersRepository.saveAndFlush(user);
    }

    /**
     * Valida se os dados de um usuário são válidos para criação.
     *
     * @param user O usuário a ser validado.
     * @throws IllegalArgumentException Se algum dado do usuário for inválido.
     */
    private void validateUserForCreation(Users user) {
        if (user == null) {
            throw new IllegalArgumentException("O usuário a ser criado não deve ser nulo.");
        }

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("O usuário a ser criado deve ter um nome.");
        }

        if (user.getUser() == null || user.getUser().isEmpty()) {
            throw new IllegalArgumentException("O usuário a ser criado deve ter um email.");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("O usuário a ser criado deve ter uma senha.");
        }
    }

    /**
     * Obtém um usuário pelo seu ID.
     *
     * @param id O ID do usuário a ser obtido.
     * @return O usuário com o ID especificado, ou nulo se não encontrado.
     */
    @Override
    public Users getUserById(Long id) {
        Optional<Users> user = usersRepository.findById(id);
        return user.orElse(null);
    }

    /**
     * Obtém um usuário pelo seu nome.
     *
     * @param name O nome do usuário a ser obtido.
     * @return O usuário com o nome especificado, ou nulo se não encontrado.
     */
    @Override
    public Users getUserByName(String name) {
        return usersRepository.findByName(name);
    }

    /**
     * Atualiza as informações de um usuário existente.
     *
     * @param user O usuário atualizado.
     * @return O usuário atualizado.
     * @throws IllegalArgumentException Se o usuário não possui um ID.
     */
    @Override
    public Users updateUser(@NotNull Users user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("O usuário a ser atualizado deve ter um ID.");
        }

        Optional<Users> existingUser = usersRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            Users updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setUser(user.getUser());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setPicture(user.getPicture());

            return usersRepository.saveAndFlush(updatedUser);
        } else {
            throw new IllegalArgumentException("Usuário não encontrado com o ID " + user.getId() + ".");
        }
    }

    /**
     * Exclui um usuário pelo seu ID.
     *
     * @param id O ID do usuário a ser excluído.
     * @return Um mapa contendo uma mensagem de sucesso após a exclusão.
     * @throws IllegalArgumentException Se o usuário não for encontrado.
     */
    @Override
    public Map<String, String> deleteUserById(Long id) {
        Optional<Users> user = usersRepository.findById(id);
        if (user.isPresent()) {
            usersRepository.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuário excluído com sucesso.");
            return response;
        } else {
            throw new IllegalArgumentException("Usuário não encontrado com o ID " + id + ".");
        }
    }
}
