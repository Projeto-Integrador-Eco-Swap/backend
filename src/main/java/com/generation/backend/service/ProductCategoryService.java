package com.generation.backend.service;

import com.generation.backend.entity.ProductCategory;
import com.generation.backend.exception.InvalidIdProductCategoryException;
import com.generation.backend.exception.InvalidNameProductCategoryException;
import com.generation.backend.exception.InvalidProductCategoryException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * Esta interface define os serviços relacionados às categorias de produtos.
 * Os serviços permitem a consulta, criação, atualização e exclusão de categorias de produtos.
 */
public interface ProductCategoryService {

    /**
     * Cria uma nova categoria de produtos.
     *
     * @param productCategory A categoria de produtos a ser criada.
     * @return A categoria de produtos criada.
     */
    ProductCategory createProductCategory(ProductCategory productCategory) throws InvalidNameProductCategoryException;

    /**
     * Recupera uma categoria de produtos pelo seu identificador único (ID).
     *
     * @param id O ID da categoria de produtos a ser recuperada.
     * @return A categoria de produtos com o ID especificado ou nulo se não encontrada.
     */
    ProductCategory getProductCategoryById(Long id) throws InvalidIdProductCategoryException;

    /**
     * Recupera uma lista de todas as categorias de produtos.
     *
     * @return Uma lista contendo todas as categorias de produtos no sistema.
     */
    List<ProductCategory> getAllProductCategories();

    /**
     * Recupera uma categoria de produtos pelo seu nome.
     *
     * @param name O nome da categoria de produtos a ser recuperada.
     * @return A categoria de produtos com o nome especificado ou nulo se não encontrada.
     */
    ProductCategory getProductCategoryByName(String name);

    /**
     * Atualiza uma categoria de produtos existente.
     *
     * @param productCategory A categoria de produtos atualizada.
     * @return A categoria de produtos atualizada.
     */
    ProductCategory updateProductCategory(ProductCategory productCategory) throws InvalidIdProductCategoryException;

    /**
     * Exclui uma categoria de produtos pelo seu identificador único (ID).
     *
     * @param id O ID da categoria de produtos a ser excluída.
     * @return Um mapa com informações sobre a operação de exclusão, como confirmação.
     */
    Map<String, String> deleteProductCategoryById(Long id);

    /**
     * Exclui uma categoria de produtos pelo seu nome.
     *
     * @param name O nome da categoria de produtos a ser excluída.
     * @return Um mapa com informações sobre a operação de exclusão, como confirmação.
     */
    Map<String, String> deleteProductCategoryByName(String name);

    /**
     * Cria uma lista de categorias de produtos.
     *
     * @param productCategories A lista de categorias de produtos a ser criada.
     * @return A lista de categorias de produtos criada.
     */
    Iterable<ProductCategory> createMultipleProductCategories(List<ProductCategory> productCategories) throws InvalidProductCategoryException;

    /**
     * Exclui todas as categorias de produtos.
     *
     * @return Um mapa com informações sobre a operação de exclusão, como confirmação.
     */
    Map<String, String> deleteAllProductCategories();

    /**
     * Atualiza a descrição de uma categoria de produtos existente.
     *
     * @param productCategory A categoria de produtos atualizada.
     * @return A categoria de produtos atualizada.
     */
    ProductCategory updateProductCategoryDescription(ProductCategory productCategory) throws InvalidIdProductCategoryException;

    /**
     * Pesquisa categorias de produtos por descrição.
     * <p>
     * Este método utiliza uma consulta JPQL (Java Persistence Query Language) para buscar categorias de produtos cuja descrição contenha o texto especificado. A consulta é executada através da anotação `@Query`, que permite definir consultas personalizadas diretamente no repositório.
     *
     * @param description A descrição a ser usada na pesquisa.
     * @return Uma lista de categorias de produtos cuja descrição contenha o texto especificado. A consulta é insensível a maiúsculas e minúsculas, o que significa que as descrições são comparadas de forma case-insensitive.
     * @throws DataAccessException Se ocorrer um erro de acesso a dados durante a execução da consulta, uma exceção do tipo DataAccessException será lançada. Essa exceção pode ocorrer em caso de problemas de conexão com o banco de dados, erros de sintaxe na consulta, entre outros.
     */
    @Query("SELECT pc FROM productcategory AS pc WHERE LOWER(pc.description) LIKE %:description%")
    List<ProductCategory> searchProductCategoriesByDescription(@Param("description") String description);

    /**
     * Obtém uma lista de categorias de produtos ordenadas pelo nome.
     * <p>
     * Este método utiliza uma consulta JPQL (Java Persistence Query Language) para buscar todas as categorias de produtos ordenadas alfabeticamente pelo nome.
     *
     * @return Uma lista de categorias de produtos ordenadas pelo nome.
     */
    @Query("SELECT pc FROM productcategory AS pc ORDER BY pc.name")
    List<ProductCategory> getProductCategoriesSortedByName();

    /**
     * Obtém uma lista de categorias de produtos por material.
     * <p>
     * Este método utiliza uma consulta JPQL (Java Persistence Query Language) para buscar categorias de produtos cujo material corresponda ao material especificado (ignorando maiúsculas e minúsculas).
     *
     * @param material O material a ser usado na filtragem.
     * @return Uma lista de categorias de produtos cujo material corresponde ao especificado.
     */
    @Query("SELECT pc FROM productcategory AS pc WHERE LOWER(pc.material) = LOWER(:material)")
    List<ProductCategory> getProductCategoriesByMaterial(@Param("material") String material);

    /**
     * Obtém uma lista de categorias de produtos por descrição exata.
     * <p>
     * Este método utiliza uma consulta JPQL (Java Persistence Query Language) para buscar categorias de produtos cuja descrição corresponda exatamente à descrição especificada (diferencia maiúsculas e minúsculas).
     *
     * @param description A descrição a ser usada na filtragem.
     * @return Uma lista de categorias de produtos cuja descrição corresponde exatamente à especificada.
     */
    @Query("SELECT pc FROM productcategory AS pc WHERE pc.description = :description")
    List<ProductCategory> getProductCategoriesByExactDescription(@Param("description") String description);
}