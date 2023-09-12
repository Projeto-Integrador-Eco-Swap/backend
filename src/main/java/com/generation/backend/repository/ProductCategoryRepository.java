package com.generation.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.generation.backend.entity.ProductCategory;
import org.springframework.stereotype.Repository;

/**
 * Repositório de categorias de produtos.
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    /**
     * Encontra uma categoria de produtos pelo seu nome.
     *
     * @param name O nome da categoria de produtos a ser encontrada.
     * @return A categoria de produtos encontrada.
     */
    ProductCategory findByName(@Param("name") String name);

    /**
     * Exclui uma categoria de produtos pelo seu nome.
     *
     * @param name O nome da categoria de produtos a ser excluída.
     */
    void deleteByName(String name);
}
