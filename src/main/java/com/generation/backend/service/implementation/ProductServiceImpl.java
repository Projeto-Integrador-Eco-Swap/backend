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

    /**
     * Cria vários produtos no repositório.
     *
     * @param products Uma coleção de produtos a serem criados.
     * @return Uma coleção de produtos criados e persistidos no repositório.
     * @throws IllegalArgumentException Se a coleção de produtos for nula ou vazia.
     */
    @Override
    public Iterable<Product> createMultipleProducts(Iterable<Product> products) {
        if (products == null || !products.iterator().hasNext()) {
            throw new IllegalArgumentException("Products to be created must not be null.");
        }

        return productRepository.saveAllAndFlush(products);
    }

    /**
     * Atualiza o preço de um produto existente.
     *
     * @param product O produto com o novo preço a ser atualizado.
     * @return O produto atualizado com o novo preço.
     * @throws IllegalArgumentException Se o ID do produto for nulo ou se o produto não existir no repositório.
     */
    @Override
    public Product updateProductPrice(@NotNull Product product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("Product to be updated must have an ID.");
        }

        Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product to be updated must exist."));

        existingProduct.setPrice(product.getPrice());

        return productRepository.saveAndFlush(existingProduct);
    }

    /**
     * Exclui todos os produtos do repositório.
     *
     * @return Um mapa contendo uma mensagem indicando que todos os produtos foram excluídos com sucesso.
     */
    @Override
    public Map<String, String> deleteAllProducts() {
        productRepository.deleteAll();
        return Map.of("message", "All products deleted successfully.");
    }

    /**
     * Exclui um produto pelo nome.
     *
     * @param name O nome do produto a ser excluído.
     * @return Um mapa contendo uma mensagem indicando que o produto com o nome especificado foi excluído com sucesso.
     */
    @Override
    public Map<String, String> deleteProductByName(String name) {
        productRepository.deleteByName(name);
        return Map.of("message", "Product with name " + name + " deleted successfully.");
    }

    /**
     * Valida se um produto é válido para criação.
     *
     * @param product O produto a ser validado.
     * @throws IllegalArgumentException Se o produto não for válido para criação.
     */
    private void validateProductForCreation(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product to be created must not be null.");
        }

        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product to be created must have a name.");
        }

        if (product.getPrice() == null) {
            throw new IllegalArgumentException("Product to be created must have a price.");
        }

        if (product.getUrl() == null || product.getUrl().isEmpty()) {
            throw new IllegalArgumentException("Product to be created must have an URL.");
        }

        if (product.getCategory() == null) {
            throw new IllegalArgumentException("Product to be created must have a category.");
        }
    }
}