package com.generation.ecoswap.repository;

import com.generation.ecoswap.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "SELECT * FROM tb_categoria WHERE descricao LIKE %:descricao%", nativeQuery = true)
    List<Categoria> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
}