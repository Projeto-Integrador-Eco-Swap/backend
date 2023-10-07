package com.generation.ecoswap.repository;

import com.generation.ecoswap.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM produtos p WHERE p.titulo LIKE %:titulo%")
    List<Produto> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}