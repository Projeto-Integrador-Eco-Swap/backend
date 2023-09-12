package com.generation.backend.service;

import com.generation.backend.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * Interface que define os serviços relacionados aos produtos.
 */
public interface ProductService {
    
    /**
     * Cria um novo produto.
     *
     * @param product O produto a ser criado.
     * @return O produto criado.
     */
    Product createProduct(Product product);

    /**
     * Recupera um produto pelo seu ID único.
     *
     * @param id O ID do produto a ser recuperado.
     * @return O produto com o ID especificado, ou null se não for encontrado.
     */
    Product getProductById(Long id);

    /**
     * Recupera uma lista de todos os produtos.
     *
     * @return Uma lista contendo todos os produtos no sistema.
     */
    List<Product> getAllProducts();

    /**
     * Recupera um produto pelo seu nome.
     *
     * @param name O nome do produto a ser recuperado.
     * @return O produto com o nome especificado, ou null se não for encontrado.
     */
    Product getProductByName(String name);

    /**
     * Atualiza um produto existente.
     *
     * @param product O produto atualizado.
     * @return O produto atualizado.
     */
    Product updateProduct(Product product);

    /**
     * Exclui um produto pelo seu ID único.
     *
     * @param id O ID do produto a ser excluído.
     */
    Map<String, String> deleteProductById(Long id);
}
