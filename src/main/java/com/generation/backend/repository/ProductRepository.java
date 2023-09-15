package com.generation.backend.repository;

import com.generation.backend.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.generation.backend.entity.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * O repositório de produtos.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Obtém um produto com base em seu nome.
     *
     * @param name O nome do produto a ser obtido.
     * @return O produto com o nome especificado, ou nulo se não for encontrado.
     */
    Product findByName(@Param("name") String name);

    /**
     * Exclui um produto com base em seu nome.
     *
     * @param name O nome do produto a ser excluído.
     */
    void deleteByName(String name);

    /**
     * Obtém uma lista de produtos pertencentes a uma categoria específica com base no ID da categoria.
     *
     * @param category O ID da categoria pela qual os produtos serão filtrados.
     * @return Uma lista de produtos que pertencem à categoria especificada.
     */
    @Query("SELECT p FROM products AS p " +
            "WHERE p.category = :category")
    List<Product> findByCategory(@Param("category") ProductCategory category);

    /**
     * Obtém uma lista de produtos com base no status de ativação.
     *
     * @param isActivated O valor booleano que indica se os produtos estão ativados (true) ou desativados (false).
     * @return Uma lista de produtos com base no status de ativação especificado.
     */
    @Query("SELECT p FROM products AS p " +
            "WHERE p.isActivated = :isActivated")
    List<Product> findProductsByActivation(@Param("isActivated") Boolean isActivated);

    /**
     * Obtém uma lista de produtos com preços dentro da faixa especificada.
     *
     * @param minPrice O preço mínimo dos produtos a serem incluídos na lista (opcional).
     * @param maxPrice O preço máximo dos produtos a serem incluídos na lista (opcional).
     * @return Uma lista de produtos com preços dentro da faixa especificada.
     */
    @Query("SELECT p FROM products AS p " +
            "WHERE p.price " +
            "BETWEEN :minPrice AND :maxPrice")
    List<Product> findProductsByPriceRange(
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice
    );
    
    /**
     * Verifica se um produto com o nome especificado existe.
     *
     * @param name O nome do produto a ser verificado.
     * @return true se um produto com o nome especificado existe, caso contrário, false.
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 " +
            "THEN true " +
            "ELSE false END " +
            "FROM products p " +
            "WHERE p.name = :name")
    boolean existsByName(@Param("name") String name);
}