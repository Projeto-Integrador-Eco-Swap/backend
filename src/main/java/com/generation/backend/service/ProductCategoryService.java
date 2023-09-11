package com.generation.backend.service;

import com.generation.backend.model.ProductCategory;

import java.util.List;
import java.util.Map;

/**
 * Esta interface define os serviços relacionados às categorias de produtos.
 * Os serviços permitem a consulta, criação, atualização e exclusão de categorias de produtos.
 */
public interface ProductCategoryService {

    /**
     * Recupera uma lista de todas as categorias de produtos.
     *
     * @return Uma lista contendo todas as categorias de produtos no sistema.
     */
    List<ProductCategory> getAllProductCategories();

    /**
     * Cria uma nova categoria de produtos.
     *
     * @param productCategory A categoria de produtos a ser criada.
     * @return A categoria de produtos criada.
     */
    ProductCategory createProductCategory(ProductCategory productCategory);

    /**
     * Recupera uma categoria de produtos pelo seu identificador único (ID).
     *
     * @param id O ID da categoria de produtos a ser recuperada.
     * @return A categoria de produtos com o ID especificado ou nulo se não encontrada.
     */
    ProductCategory getProductCategoryById(Long id);

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
    ProductCategory updateProductCategory(ProductCategory productCategory);

    /**
     * Exclui uma categoria de produtos pelo seu identificador único (ID).
     *
     * @param id O ID da categoria de produtos a ser excluída.
     * @return Um mapa com informações sobre a operação de exclusão, como confirmação.
     */
    Map<String, String> deleteProductCategoryById(Long id);
}