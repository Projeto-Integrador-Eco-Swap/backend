package com.generation.ecoswap.repository;


import com.generation.ecoswap.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM tb_usuario WHERE usuario = ?1", nativeQuery = true)
    Optional<Usuario> findByUsuario(String usuario);
}