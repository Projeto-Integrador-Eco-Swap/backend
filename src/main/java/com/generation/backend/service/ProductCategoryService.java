package com.generation.backend.service;

import com.generation.backend.entity.ProductCategory;
import com.generation.backend.exception.InvalidIdProductCategoryException;
import com.generation.backend.exception.InvalidNameProductCategoryException;
import com.generation.backend.exception.InvalidProductCategoryException;
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
     * Realiza uma pesquisa de categorias de produtos com base em uma descrição parcial, ignorando maiúsculas e minúsculas.
     *
     * @param description A descrição parcial pela qual as categorias serão filtradas.
     * @return Uma lista de categorias de produtos correspondentes à descrição parcial especificada.
     */
    @Query("SELECT pc FROM productcategory pc WHERE LOWER(pc.description) LIKE %:description%")
    List<ProductCategory> searchProductCategoriesByDescription(@Param("description") String description);

    /**
     * Retorna uma lista de categorias de produtos ordenadas pelo nome.
     *
     * @return Uma lista de categorias de produtos ordenadas alfabeticamente pelo nome.
     */
    @Query("SELECT pc FROM productcategory pc ORDER BY pc.name")
    List<ProductCategory> getProductCategoriesSortedByName();

    /**
     * Retorna uma lista de categorias de produtos com base em um material específico, ignorando maiúsculas e minúsculas.
     *
     * @param material O material pelo qual as categorias serão filtradas.
     * @return Uma lista de categorias de produtos correspondentes ao material especificado.
     */
    @Query("SELECT pc FROM productcategory pc WHERE LOWER(pc.material) = LOWER(:material)")
    List<ProductCategory> getProductCategoriesByMaterial(@Param("material") String material);

    /**
     * Retorna uma lista de categorias de produtos com base em uma descrição exata.
     *
     * @param description A descrição exata pela qual as categorias serão filtradas.
     * @return Uma lista de categorias de produtos correspondentes à descrição exata especificada.
     */
    @Query("SELECT pc FROM productcategory pc WHERE pc.description = :description")
    List<ProductCategory> getProductCategoriesByExactDescription(@Param("description") String description);
}