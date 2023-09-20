package com.generation.backend.repository;

import com.generation.backend.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

/**
 * Classe de testes para o repositório de usuários.
 *
 * <p>Esta classe utiliza a anotação {@code @SpringBootTest} para indicar que os testes
 * devem ser executados com o contexto do Spring Boot. A anotação {@code @Autowired}
 * injeta uma instância de {@code UserRepository} para ser utilizada nos testes.</p>
 *
 * <p>Os testes são executados em ordem alfabética, por isso os métodos estão nomeados
 * com um prefixo para indicar a ordem de execução.</p>
 */
@SpringBootTest
public class UserRepositoryTest {

    /**
     * Injeta uma instância de {@code UserRepository} para ser utilizada nos testes.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Testa e documenta o salvamento de um novo usuário.
     */
    @Test
    void saveMethod() {

    }

    /**
     * Testa o método de salvar uma lista de usuários em lote.
     */
    @Test
    void saveAllMethod() {
    }


    /**
     * Testa e imprime todos os usuários cadastrados.
     */
    @Test
    void getAll() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * Testa e imprime se um usuário com o ID especificado existe no banco de dados.
     */
    @Test
    void existsByIdMethod() {
        Long userId = 2L; // Substitua pelo ID desejado
        boolean exists = userRepository.existsById(userId);
        System.out.println("O usuário com ID " + userId + " existe: " + exists);
    }

    /**
     * Testa e imprime a contagem total de usuários no banco de dados.
     */
    @Test
    void countMethod() {
        long userCount = userRepository.count();
        System.out.println("Total de usuários no banco de dados: " + userCount);
    }

    /**
     * Testa e imprime todos os usuários cadastrados.
     */
    @Test
    void findAllMethod() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * Testa e imprime o usuário com o ID especificado.
     */
    @Test
    void findByIdMethod() {
        Long userId = 2L; // Substitua pelo ID desejado
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println("Usuário encontrado com ID " + userId + ":");
            System.out.println(user);
        } else {
            System.out.println("Nenhum usuário encontrado com ID " + userId);
        }
    }

    /**
     * Testa o método de atualizar um usuário.
     */
    @Test
    void updateMethod() {
        Optional<User> optionalUser = userRepository.findById(1L);
        Assertions.assertTrue(optionalUser.isPresent(), "O usuário com ID 2 deve existir.");

        User user1 = optionalUser.get();
        user1.setFirstName("Vinicius");

        User updatedUser = userRepository.save(user1);
        Assertions.assertEquals("Vinicius", updatedUser.getFirstName(), "O primeiro nome do usuário deve ser atualizado.");
    }

    /**
     * Testa a exclusão de um usuário pelo ID e imprime o resultado.
     *
     * <p>Este teste utiliza a anotação {@code @Transactional} para garantir que a operação
     * de exclusão seja desfeita após a execução do teste, evitando alterações permanentes
     * no banco de dados de teste.</p>
     */
    @Test
    @Transactional
    void deleteByIdMethod() {
        Long userIdToDelete = 2L; // Substitua pelo ID do usuário que deseja excluir
        Optional<User> userOptional = userRepository.findById(userIdToDelete);

        if (userOptional.isPresent()) {
            userRepository.deleteById(userIdToDelete);
            System.out.println("Usuário com ID " + userIdToDelete + " excluído com sucesso.");
        } else {
            System.out.println("Nenhum usuário encontrado com ID " + userIdToDelete + ".");
        }
    }

    /**
     * Testa o método de exclusão de todos os usuários do repositório.
     *
     * <p>Este teste utiliza a anotação {@code @Transactional} para garantir que as operações
     * de exclusão sejam desfeitas após a execução do teste, evitando alterações permanentes
     * no banco de dados de teste. Após a exclusão, o teste verifica se o número total de
     * usuários é igual a zero.</p>
     */
    @Test
    @Transactional
    void deleteAllMethod() {
        userRepository.deleteAll();
        long userCount = userRepository.count();
        Assertions.assertEquals(0, userCount, "Todos os usuários devem ser excluídos.");
    }


}