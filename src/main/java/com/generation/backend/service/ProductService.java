package com.generation.backend.service;

import com.generation.backend.model.Products;

import java.util.List;
import java.util.Map;

/**
 * Interface que define os serviços relacionados aos produtos.
 */
public interface ProductService {

    /**
     * Recupera uma lista de todos os produtos.
     *
     * @return Uma lista contendo todos os produtos no sistema.
     */
    List<Products> getAllProducts();

    /**
     * Cria um novo produto.
     *
     * @param product O produto a ser criado.
     * @return O produto criado.
     */
    Products createProduct(Products product);

    /**
     * Recupera um produto pelo seu ID único.
     *
     * @param id O ID do produto a ser recuperado.
     * @return O produto com o ID especificado, ou null se não for encontrado.
     */
    Products getProductById(Long id);

    /**
     * Recupera um produto pelo seu nome.
     *
     * @param name O nome do produto a ser recuperado.
     * @return O produto com o nome especificado, ou null se não for encontrado.
     */
    Products getProductByName(String name);

    /**
     * Atualiza um produto existente.
     *
     * @param product O produto atualizado.
     * @return O produto atualizado.
     */
    Products updateProduct(Products product);

    /**
     * Exclui um produto pelo seu ID único.
     *
     * @param id O ID do produto a ser excluído.
     */
    Map<String, String> deleteProductById(Long id);
}
