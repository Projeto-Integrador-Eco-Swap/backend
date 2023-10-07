package com.generation.ecoswap.repository;


import com.generation.ecoswap.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM usuarios u WHERE u.usuario = :usuario")
    Optional<Usuario> findByUsuario(@Param("usuario") String usuario);

    @Query("SELECT u FROM usuarios u WHERE u.nome = :nome")
    List<Usuario> findByNome(String nome);
}