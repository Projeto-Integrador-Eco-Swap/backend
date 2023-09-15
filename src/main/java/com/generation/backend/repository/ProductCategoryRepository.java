package com.generation.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.generation.backend.entity.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * Verifica se uma categoria de produtos com um determinado nome existe.
     *
     * @param name O nome da categoria de produtos a ser verificado.
     * @return `true` se a categoria de produtos existe, caso contrário `false`.
     */
    boolean existsByName(String name);

    /**
     * Pesquisa categorias de produtos com base em uma descrição parcial.
     *
     * @param description A descrição parcial para pesquisa.
     * @return Uma lista de categorias de produtos correspondentes à descrição parcial.
     */
    @Query("SELECT pc FROM productcategory AS pc " +
            "WHERE pc.name " +
            "LIKE %:description%")
    List<ProductCategory> searchProductCategoriesByDescription(@Param("description") String description);

    /**
     * Obtém uma lista de categorias de produtos ordenadas por nome.
     *
     * @return Uma lista de categorias de produtos ordenadas por nome.
     */
    @Query("SELECT pc FROM productcategory AS pc " +
            "ORDER BY pc.name")
    List<ProductCategory> getProductCategoriesSortedByName();

    /**
     * Obtém uma lista de categorias de produtos com base no material.
     *
     * @param material O material pelo qual as categorias de produtos devem ser filtradas.
     * @return Uma lista de categorias de produtos que correspondem ao material especificado.
     */
    @Query("SELECT pc FROM productcategory AS pc " +
            "WHERE pc.material = :material")
    List<ProductCategory> getProductCategoriesByMaterial(@Param("material") String material);

    /**
     * Obtém uma lista de categorias de produtos com base em uma descrição exata.
     *
     * @param description A descrição exata para pesquisa.
     * @return Uma lista de categorias de produtos correspondentes à descrição exata.
     */
    @Query("SELECT pc FROM productcategory AS pc " +
            "WHERE pc.description = :description")
    List<ProductCategory> getProductCategoriesByExactDescription(@Param("description") String description);
}
