package com.generation.ecoswap.repository;

import com.generation.ecoswap.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT c FROM categorias c WHERE c.descricao LIKE %:descricao%")
    Optional<Categoria> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
}
