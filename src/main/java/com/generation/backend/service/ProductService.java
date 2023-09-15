package com.generation.backend.service;

import com.generation.backend.entity.Product;
import com.generation.backend.entity.ProductCategory;

import java.math.BigDecimal;
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

    /**
     * Exclui todos os produtos.
     */
    Iterable<Product> createMultipleProducts(Iterable<Product> products);

    /**
     * Atualiza o preço de um produto existente.
     *
     * @param product O produto atualizado.
     * @return O produto atualizado.
     */
    Product updateProductPrice(Product product);

    /**
     * Exclui todos os produtos.
     */
    Map<String, String> deleteAllProducts();

    /**
     * Exclui um produto pelo seu nome.
     *
     * @param name O nome do produto a ser excluído.
     */
    Map<String, String> deleteProductByName(String name);

    /**
     * Obtém uma lista de produtos pertencentes a uma categoria específica com base no objeto de categoria.
     *
     * @param category O objeto de categoria pela qual os produtos serão filtrados.
     * @return Uma lista de produtos que pertencem à categoria especificada.
     */
    List<Product> getProductsByCategory(ProductCategory category);


    /**
     * Obtém uma lista de produtos com base no status de ativação.
     *
     * @param isActivated O valor booleano que indica se os produtos estão ativados (true) ou desativados (false).
     * @return Uma lista de produtos com base no status de ativação especificado.
     */
    List<Product> getProductsByActivation(Boolean isActivated);

    /**
     * Obtém uma lista de produtos com preços dentro da faixa especificada.
     *
     * @param minPrice O preço mínimo dos produtos a serem incluídos na lista (opcional).
     * @param maxPrice O preço máximo dos produtos a serem incluídos na lista (opcional).
     * @return Uma lista de produtos com preços dentro da faixa especificada.
     */
    List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
}