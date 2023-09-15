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
    @Query("SELECT u FROM users AS u " +
            "WHERE u.firstName = :name")
    User findByFirstName(String name);

    /**
     * Obtém um usuário pelo seu sobrenome.
     *
     * @param lastName O sobrenome do usuário a ser obtido.
     * @return O usuário com o sobrenome especificado, ou null se não encontrado.
     */
    @Query("SELECT u FROM users AS u " +
            "WHERE u.lastName = :lastName")
    User findUserByLastName(String lastName);
    
    /**
     * Obtém um usuário pela sua data de nascimento.
     *
     * @param birthDate A data de nascimento do usuário a ser obtido.
     * @return O usuário com a data de nascimento especificada, ou null se não encontrado.
     */
    @Query("SELECT u FROM users AS u " +
            "WHERE u.birthDate = :birthDate")
    User findByBirthDate(@Param("birthDate") LocalDate birthDate);

    /**
     * Obtém um usuário pelo seu email.
     *
     * @param email O email do usuário a ser obtido.
     * @return O usuário com o email especificado, ou null se não encontrado.
     */
    @Query("SELECT u FROM users AS u " +
            "WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
}
