package com.generation.backend.service.implementation;

import com.generation.backend.model.Products;
import com.generation.backend.service.ProductService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import com.generation.backend.repository.ProductsRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A implementação do serviço de produto.
 */
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * O repositório de produtos.
     */
    private final ProductsRepository productsRepository;

    /**
     * Cria um novo serviço de produto.
     *
     * @param productsRepository O repositório de produtos.
     */
    public ProductServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    /**
     * Recupera uma lista de todos os produtos.
     *
     * @return Uma lista contendo todos os produtos no sistema.
     */
    @Override
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    /**
     * Cria um novo produto.
     *
     * @param product O produto a ser criado.
     * @return O produto criado.
     */
    @Override
    public Products createProduct(Products product) {
        validateProductForCreation(product);

        if (product.getId() != null) {
            throw new IllegalArgumentException("Product to be created must not have an ID.");
        }

        return productsRepository.saveAndFlush(product);
    }

    private void validateProductForCreation(Products product) {
    }

    /**
     * Recupera um produto pelo seu ID único.
     *
     * @param id O ID do produto a ser recuperado.
     * @return O produto com o ID especificado, ou null se não for encontrado.
     */
    @Override
    public Products getProductById(Long id) {
        Optional<Products> optionalProduct = productsRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    /**
     * Recupera um produto pelo seu nome.
     *
     * @param name O nome do produto a ser recuperado.
     * @return O produto com o nome especificado, ou null se não for encontrado.
     */
    @Override
    public Products getProductByName(String name) {
        return productsRepository.findByName(name);
    }

    /**
     * Atualiza um produto existente.
     *
     * @param product O produto atualizado.
     * @return O produto atualizado.
     */
    @Override
    public Products updateProduct(@NotNull Products product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("Product to be updated must have an ID.");
        }

        Products existingProduct = productsRepository.findById(product.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product to be updated must exist."));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setAmount(product.getAmount());
        existingProduct.setPrice(product.getPrice());

        return productsRepository.saveAndFlush(existingProduct);
    }

    /**
     * Exclui um produto pelo seu ID único.
     *
     * @param id O ID do produto a ser excluído.
     */
    @Override
    public Map<String, String> deleteProductById(Long id) {
        productsRepository.deleteById(id);
        return Map.of("message", "Product deleted successfully.");
    }
}
