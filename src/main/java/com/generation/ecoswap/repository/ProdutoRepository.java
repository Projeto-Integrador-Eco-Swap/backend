package com.generation.ecoswap.repository;

import com.generation.ecoswap.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(value = "SELECT * FROM tb_produto WHERE titulo LIKE %:titulo%", nativeQuery = true)
    List<Produto> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}