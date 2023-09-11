package com.generation.backend.service.implementation;

import com.generation.backend.model.ProductCategory;
import com.generation.backend.repository.ProductCategoryRepository;
import com.generation.backend.service.ProductCategoryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The implementation of the product category service.
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    /**
     * The repository for product categories.
     */
    private final ProductCategoryRepository productCategoryRepository;

    /**
     * Creates a new product category service.
     *
     * @param productCategoryRepository The repository for product categories.
     */
    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    /**
     * Retrieves a list of all product categories.
     *
     * @return A list containing all product categories in the system.
     */
    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    /**
     * Creates a new product category.
     *
     * @param productCategory The product category to be created.
     * @return The created product category.
     * @throws IllegalArgumentException if the product category is not valid for creation.
     */
    @Override
    public ProductCategory createProductCategory(@NotNull ProductCategory productCategory) {
        validateProductCategoryForCreation(productCategory);

        if (productCategory.getId() != null) {
            throw new IllegalArgumentException("Product category to be created must not have an ID.");
        }

        return productCategoryRepository.saveAndFlush(productCategory);
    }

    private void validateProductCategoryForCreation(@NotNull ProductCategory productCategory) {
        if (productCategory.getName() == null || productCategory.getName().isEmpty()) {
            throw new IllegalArgumentException("Product category name must not be null or empty.");
        }
    }

    /**
     * Retrieves a product category by its unique identifier (ID).
     *
     * @param id The ID of the product category to retrieve.
     * @return The product category with the specified ID, or null if not found.
     */
    @Override
    public ProductCategory getProductCategoryById(Long id) {
        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findById(id);
        return optionalProductCategory.orElse(null);
    }

    /**
     * Retrieves a product category by its name.
     *
     * @param name The name of the product category to retrieve.
     * @return The product category with the specified name, or null if not found.
     */
    @Override
    public ProductCategory getProductCategoryByName(String name) {
        return productCategoryRepository.findByName(name);
    }

    /**
     * Updates an existing product category.
     *
     * @param productCategory The updated product category.
     * @return The updated product category.
     * @throws IllegalArgumentException if the product category is not valid for updating.
     */
    @Override
    public ProductCategory updateProductCategory(@NotNull ProductCategory productCategory) {
        if (productCategory.getId() == null) {
            throw new IllegalArgumentException("Product category to be updated must have an ID.");
        }

        ProductCategory existingProductCategory = productCategoryRepository.findById(productCategory.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cannot find product category with ID " + productCategory.getId() + "."));

        // Update the properties of the existing product category
        existingProductCategory.setName(productCategory.getName());
        existingProductCategory.setDescription(productCategory.getDescription());
        existingProductCategory.setMaterial(productCategory.getMaterial());

        return productCategoryRepository.saveAndFlush(existingProductCategory);
    }

    /**
     * Deletes a product category by its unique identifier (ID).
     *
     * @param id The ID of the product category to delete.
     * @return
     */
    @Override
    public Map<String, String> deleteProductCategoryById(Long id) {
        productCategoryRepository.deleteById(id);
        return null;
    }
}
