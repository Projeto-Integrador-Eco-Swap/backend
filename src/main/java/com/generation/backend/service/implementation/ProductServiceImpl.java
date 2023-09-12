package com.generation.backend.service.implementation;

import com.generation.backend.entity.Product;
import com.generation.backend.repository.ProductRepository;
import com.generation.backend.service.ProductService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

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
    private final ProductRepository productRepository;

    /**
     * Cria um novo serviço de produto.
     *
     * @param productRepository O repositório de produtos.
     */
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Recupera uma lista de todos os produtos.
     *
     * @return Uma lista contendo todos os produtos no sistema.
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Cria um novo produto.
     *
     * @param product O produto a ser criado.
     * @return O produto criado.
     */
    @Override
    public Product createProduct(Product product) {
        validateProductForCreation(product);

        if (product.getId() != null) {
            throw new IllegalArgumentException("Product to be created must not have an ID.");
        }

        return productRepository.saveAndFlush(product);
    }

    private void validateProductForCreation(Product product) {
    }

    /**
     * Recupera um produto pelo seu ID único.
     *
     * @param id O ID do produto a ser recuperado.
     * @return O produto com o ID especificado, ou null se não for encontrado.
     */
    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    /**
     * Recupera um produto pelo seu nome.
     *
     * @param name O nome do produto a ser recuperado.
     * @return O produto com o nome especificado, ou null se não for encontrado.
     */
    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    /**
     * Atualiza um produto existente.
     *
     * @param product O produto atualizado.
     * @return O produto atualizado.
     */
    @Override
    public Product updateProduct(@NotNull Product product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("Product to be updated must have an ID.");
        }

        Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product to be updated must exist."));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setUrl(product.getUrl());
        existingProduct.setActivated(product.isActivated());
        existingProduct.setCategory(product.getCategory());
        
        return productRepository.saveAndFlush(existingProduct);
    }

    /**
     * Exclui um produto pelo seu ID único.
     *
     * @param id O ID do produto a ser excluído.
     */
    @Override
    public Map<String, String> deleteProductById(Long id) {
        productRepository.deleteById(id);
        return Map.of("message", "Product deleted successfully.");
    }
}