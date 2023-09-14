package com.generation.backend.service.implementation;

import com.generation.backend.entity.User;
import com.generation.backend.repository.UserRepository;
import com.generation.backend.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    private final UserRepository userRepository;

    /**
     * Cria um novo serviço de usuário.
     *
     * @param userRepository O repositório de usuários.
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Obtém todos os usuários cadastrados no sistema.
     *
     * @return Uma lista de todos os usuários.
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Cria um novo usuário no sistema.
     *
     * @param user O usuário a ser criado.
     * @return O usuário criado.
     * @throws IllegalArgumentException Se o usuário já possui um ID.
     */
    @Override
    public User createUser(User user) {
        validateUserForCreation(user);

        if (user.getId() != null) {
            throw new IllegalArgumentException("O usuário a ser criado não deve ter um ID.");
        }

        return userRepository.saveAndFlush(user);
    }

    /**
     * Obtém um usuário pelo seu ID.
     *
     * @param id O ID do usuário a ser obtido.
     * @return O usuário com o ID especificado, ou nulo se não encontrado.
     */
    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    /**
     * Obtém um usuário pelo seu nome.
     *
     * @param name O nome do usuário a ser obtido.
     * @return O usuário com o nome especificado, ou nulo se não encontrado.
     */
    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    /**
     * Atualiza as informações de um usuário existente.
     *
     * @param user O usuário atualizado.
     * @return O usuário atualizado.
     * @throws IllegalArgumentException Se o usuário não possui um ID ou não for encontrado.
     */
    @Override
    public User updateUser(@NotNull User user) {
        validateUserIdForUpdate(user);

        User existingUser = findUserById(user.getId());

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setPicture(user.getPicture());

        return userRepository.saveAndFlush(existingUser);
    }

    /**
     * Atualiza a data de nascimento de um usuário existente.
     *
     * @param user O usuário com a nova data de nascimento.
     * @return O usuário atualizado com a nova data de nascimento.
     * @throws IllegalArgumentException Se o usuário não tiver um ID válido ou não for encontrado.
     */
    @Override
    public User updateUserBirthDate(@NotNull User user) {
        validateUserIdForUpdate(user);

        User existingUser = findUserById(user.getId());

        existingUser.setBirthDate(user.getBirthDate());

        return userRepository.saveAndFlush(existingUser);
    }

    /**
     * Valida se o usuário possui um ID válido para atualização.
     *
     * @param user O usuário a ser validado.
     * @throws IllegalArgumentException Se o usuário não possuir um ID válido.
     */
    private void validateUserIdForUpdate(@NotNull User user) {
        if (user.getId() == null || user.getId() <= 0) {
            throw new IllegalArgumentException("O usuário a ser atualizado deve ter um ID válido.");
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
        User user = findUserById(id);
        userRepository.delete(user);
        return createSuccessResponse();
    }

    /**
     * Exclui um usuário pelo seu nome.
     *
     * @param name O nome do usuário a ser excluído.
     * @return Um mapa contendo uma mensagem de status da exclusão.
     * @throws IllegalArgumentException Se o usuário não for encontrado.
     */
    @Override
    public Map<String, String> deleteUserByName(String name) {
        User user = findUserByName(name);
        userRepository.delete(user);
        return createSuccessResponse();
    }

    /**
     * Atualiza a senha de um usuário existente.
     *
     * @param user O usuário atualizado.
     * @return O usuário atualizado.
     */
    @Override
    public User updateUserPassword(@NotNull User user) {
        User existingUser = findUserById(user.getId());

        existingUser.setPassword(user.getPassword());

        return userRepository.saveAndFlush(existingUser);
    }

    /**
     * Obtém um usuário pelo seu nome e senha.
     *
     * @param name     O nome do usuário a ser obtido.
     * @param password A senha do usuário a ser obtido.
     * @return O usuário com o nome e senha especificados, ou null se não encontrado.
     */
    @Override
    public User getUserByNameAndPassword(String name, String password) {
        return userRepository.findByNameAndPassword(name, password);
    }

    /**
     * Procura um usuário pelo nome e lança uma exceção se não for encontrado.
     *
     * @param name  O nome do usuário a ser encontrado.
     * @param email
     * @return O usuário encontrado.
     * @throws IllegalArgumentException Se o usuário não for encontrado.
     */
    @Override
    public User getUserByNameAndEmail(String name, String email) {
        return userRepository.findByNameAndEmail(name, email);
    }

    /**
     * Obtém um usuário pela sua data de nascimento.
     *
     * @param birthDate A data de nascimento do usuário a ser obtido.
     * @return O usuário com a data de nascimento especificada, ou null se não encontrado.
     */
    @Override
    public User getUserByBirthDay(String birthDate) {
        return userRepository.findByBirthDate(LocalDate.parse(birthDate));
    }


    /**
     * Exclui um usuário pelo seu nome e senha.
     *
     * @param name     O nome do usuário a ser excluído.
     * @param password A senha do usuário a ser excluído.
     * @return Um ResponseEntity vazio (sem corpo) indicando sucesso.
     */
    @Override
    public void deleteUserByNameAndPassword(String name, String password) {
        User userToDelete = userRepository.findByNameAndPassword(name, password);

        if (userToDelete != null) {
            userRepository.delete(userToDelete);
        } else {
            throw new IllegalArgumentException("Usuário com o nome e senha especificados não encontrado.");
        }
    }

    /**
     * Exclui um usuário pelo seu nome e email.
     *
     * @param name  O nome do usuário a ser excluído.
     * @param email O email do usuário a ser excluído.
     * @return Um ResponseEntity vazio (sem corpo) indicando sucesso.
     */
    @Override
    public void deleteUserByNameAndEmail(String name, String email) {
        User userToDelete = userRepository.findByNameAndEmail(name, email);

        if (userToDelete != null) {
            userRepository.delete(userToDelete);
        } else {
            throw new IllegalArgumentException("Usuário com o nome e email especificados não encontrado.");
        }
    }

    /**
     * Exclui um usuário pela sua data de nascimento.
     *
     * @param birthDate A data de nascimento do usuário a ser excluído.
     * @return Um ResponseEntity vazio (sem corpo) indicando sucesso.
     */
    public void deleteUserByBirthDay(String birthDate) {
        User userToDelete = userRepository.findByBirthDate(LocalDate.parse(birthDate));

        if (userToDelete != null) {
            // Se o usuário for encontrado, exclua-o.
            userRepository.delete(userToDelete);
        } else {
            // Caso contrário, lance uma exceção ou lide com a situação de usuário não encontrado de outra forma.
            throw new IllegalArgumentException("Usuário com a data de nascimento especificada não encontrado.");
        }
    }

    /**
     * Exclui todos os usuários.
     */
    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    /**
     * Procura um usuário pelo ID e lança uma exceção se não for encontrado.
     *
     * @param id O ID do usuário a ser encontrado.
     * @return O usuário encontrado.
     * @throws IllegalArgumentException Se o usuário não for encontrado.
     */
    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o ID " + id + ". Não foi possível excluí-lo."));
    }

    /**
     * Procura um usuário pelo nome e lança uma exceção se não for encontrado.
     *
     * @param name O nome do usuário a ser encontrado.
     * @return O usuário encontrado.
     * @throws IllegalArgumentException Se o usuário não for encontrado.
     */
    private User findUserByName(String name) {
        return userRepository.findByName(name);
    }

    /**
     * Cria um mapa de resposta com uma mensagem de sucesso.
     *
     * @return Um mapa de resposta com a mensagem.
     */
    private @NotNull Map<String, String> createSuccessResponse() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário excluído com sucesso.");
        return response;
    }

    /**
     * Valida se os dados de um usuário são válidos para a criação.
     *
     * @param user O usuário a ser validado.
     * @throws IllegalArgumentException Se algum dado do usuário for inválido.
     */
    private void validateUserForCreation(User user) {
        if (user == null) {
            throw new IllegalArgumentException("O usuário a ser criado não deve ser nulo.");
        }

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode estar em branco.");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O email do usuário não pode estar em branco.");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("A senha do usuário não pode estar em branco.");
        }
    }
}