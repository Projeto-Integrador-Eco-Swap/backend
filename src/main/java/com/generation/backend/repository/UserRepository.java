package com.generation.backend.repository;

import com.generation.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Obtém um usuário pelo seu nome.
     *
     * @param name O nome do usuário a ser obtido.
     * @return O usuário com o nome especificado, ou null se não encontrado.
     */
    User findByName(@Param("name") String name);

    /**
     * Obtém um usuário pelo seu nome e senha.
     *
     * @param name O nome do usuário a ser obtido.
     * @param password A senha do usuário a ser obtido.
     * @return O usuário com o nome e senha especificados, ou null se não encontrado.
     */
    User findByNameAndPassword(@Param("name") String name,@Param("password") String password);

    /**
     * Obtém um usuário pelo seu nome e email.
     *
     * @param name O nome do usuário a ser obtido.
     * @param email O email do usuário a ser obtido.
     * @return O usuário com o nome e email especificados, ou null se não encontrado.
     */
    User findByNameAndEmail(String name, String email);

    /**
     * Obtém um usuário pela sua data de nascimento.
     *
     * @param birthDate A data de nascimento do usuário a ser obtido.
     * @return O usuário com a data de nascimento especificada, ou null se não encontrado.
     */
    @Query("SELECT u FROM users u WHERE u.birthDate = :birthDate")
    User findByBirthDate(@Param("birthDate") LocalDate birthDate);
    
    User findByEmail(@Param("email") String email);
}
