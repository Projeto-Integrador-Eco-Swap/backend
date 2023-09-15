package com.generation.backend.repository;

import com.generation.backend.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @Transactional
    void saveMethod() {
        User user1 = new User();
        user1.setFirstName("João");
        user1.setLastName("Silva");
        user1.setEmail("vinicius_andrade2010@hotmail.com");
        user1.setPassword("123456");
        user1.setPhone("11999999999");
        user1.setBirthDate(LocalDate.of(1999, 10, 10));

        User savedUser = userRepository.save(user1);

        System.out.println("Usuário Salvo:");
        System.out.println(savedUser);

        assert savedUser.getId() != null : "O ID do usuário deve ser gerado após o salvamento.";
    }

    /**
     * Testa o método de salvar uma lista de usuários em lote.
     */
    @Test
    void saveAllMethod() {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setFirstName("Usuário" + (i + 1));
            user.setLastName("Sobrenome");
            user.setEmail("usuario" + (i + 1) + "@example.com");
            user.setPassword("password123");
            user.setPhone("11999999999");
            user.setBirthDate(LocalDate.of(1999, 10, 10));
            users.add(user);
        }

        userRepository.saveAll(users);
        System.out.println(users);
        Assertions.assertFalse(users.isEmpty(), "A lista de usuários não deve estar vazia após salvar.");
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
        Optional<User> optionalUser = userRepository.findById(2L);
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

    /**
     * Testa o método de exclusão em lote de usuários pelo ID.
     *
     * <p>Este teste utiliza a anotação {@code @Transactional} para garantir que as operações
     * de exclusão sejam desfeitas após a execução do teste, evitando alterações permanentes
     * no banco de dados de teste. Após a exclusão em lote, o teste verifica se o número
     * total de usuários corresponde ao esperado.</p>
     */
    @Test
    @Transactional
    void deleteAllByIdMethod() {
        List<Long> userIds = List.of(1L, 3L); // IDs dos usuários a serem excluídos

        userRepository.deleteAllById(userIds);
        long userCount = userRepository.count();
        Assertions.assertEquals(3, userCount, "Apenas os usuários com ID 1 e 3 devem ser excluídos.");
    }
}