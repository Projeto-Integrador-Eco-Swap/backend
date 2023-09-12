package com.generation.backend.service.implementation;

import com.generation.backend.entity.ProductCategory;
import com.generation.backend.repository.ProductCategoryRepository;
import com.generation.backend.service.ProductCategoryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementação do serviço de categoria de produtos.
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    /**
     * Repositório de categorias de produtos.
     */
    private final ProductCategoryRepository productCategoryRepository;

    /**
     * Cria um novo serviço de categoria de produtos.
     *
     * @param productCategoryRepository Repositório de categorias de produtos.
     */
    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    /**
     * Obtém uma lista de todas as categorias de produtos.
     *
     * @return Uma lista contendo todas as categorias de produtos no sistema.
     */
    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    /**
     * Cria uma nova categoria de produtos.
     *
     * @param productCategory A categoria de produtos a ser criada.
     * @return A categoria de produtos criada.
     * @throws IllegalArgumentException Se a categoria de produtos não for válida para criação.
     */
    @Override
    public ProductCategory createProductCategory(@NotNull ProductCategory productCategory) {
        validateProductCategoryForCreation(productCategory);

        if (productCategory.getId() != null) {
            throw new IllegalArgumentException("A categoria de produtos a ser criada não deve ter um ID.");
        }

        return productCategoryRepository.saveAndFlush(productCategory);
    }

    /**
     * Valida uma categoria de produtos para criação.
     *
     * @param productCategory A categoria de produtos a ser validada.
     * @throws IllegalArgumentException Se a categoria de produtos não for válida para criação.
     */
    private void validateProductCategoryForCreation(@NotNull ProductCategory productCategory) {
        if (productCategory.getName() == null || productCategory.getName().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria de produtos não deve ser nulo ou vazio.");
        }
    }

    /**
     * Obtém uma categoria de produtos pelo seu identificador único (ID).
     *
     * @param id O ID da categoria de produtos a ser recuperada.
     * @return A categoria de produtos com o ID especificado, ou null se não encontrada.
     */
    @Override
    public ProductCategory getProductCategoryById(Long id) {
        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findById(id);
        return optionalProductCategory.orElse(null);
    }

    /**
     * Obtém uma categoria de produtos pelo nome.
     *
     * @param name O nome da categoria de produtos a ser recuperada.
     * @return A categoria de produtos com o nome especificado, ou null se não encontrada.
     */
    @Override
    public ProductCategory getProductCategoryByName(String name) {
        return productCategoryRepository.findByName(name);
    }

    /**
     * Atualiza uma categoria de produtos existente.
     *
     * @param productCategory A categoria de produtos atualizada.
     * @return A categoria de produtos atualizada.
     * @throws IllegalArgumentException Se a categoria de produtos não for válida para atualização.
     */
    @Override
    public ProductCategory updateProductCategory(@NotNull ProductCategory productCategory) {
        if (productCategory.getId() == null) {
            throw new IllegalArgumentException("A categoria de produtos a ser atualizada deve ter um ID.");
        }

        ProductCategory existingProductCategory = productCategoryRepository.findById(productCategory.getId())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possível encontrar a categoria de produtos com o ID " + productCategory.getId() + "."));

        existingProductCategory.setName(productCategory.getName());
        existingProductCategory.setDescription(productCategory.getDescription());
        existingProductCategory.setMaterial(productCategory.getMaterial());

        return productCategoryRepository.saveAndFlush(existingProductCategory);
    }

    /**
     * Exclui uma categoria de produtos pelo seu identificador único (ID).
     *
     * @param id O ID da categoria de produtos a ser excluída.
     * @return Um mapa contendo uma mensagem de confirmação após a exclusão.
     */
    @Override
    public Map<String, String> deleteProductCategoryById(Long id) {
        productCategoryRepository.deleteById(id);
        return Map.of("message", "Categoria de produtos com o ID " + id + " foi excluída.");
    }

    /**
     * Exclui uma categoria de produtos pelo seu nome.
     *
     * @param name O nome da categoria de produtos a ser excluída.
     * @return Um mapa contendo uma mensagem de confirmação após a exclusão.
     */
    @Override
    public Map<String, String> deleteProductCategoryByName(String name) {
        productCategoryRepository.deleteByName(name);
        return Map.of("message", "Categoria de produtos com o nome " + name + " foi excluída.");
    }
}