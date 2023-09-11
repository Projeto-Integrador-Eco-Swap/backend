package com.generation.backend.controller;

import com.generation.backend.model.ProductCategory;
import com.generation.backend.service.ProductCategoryService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controlador responsável por gerenciar endpoints relacionados às categorias de produtos.
 */
@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductCategoryController {

    /**
     * O serviço para categorias de produtos.
     */
    private final ProductCategoryService productCategoryService;

    /**
     * Cria um novo controlador de categorias de produtos.
     *
     * @param productCategoryService O serviço para categorias de produtos.
     */
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    /**
     * Cria uma nova categoria de produtos.
     *
     * @param productCategory A categoria de produtos a ser criada.
     * @return A categoria de produtos criada.
     */
    @PostMapping("/create")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) {
        ProductCategory createdProductCategory = productCategoryService.createProductCategory(productCategory);
        return ResponseEntity.status(HttpStatus.OK).body(createdProductCategory);
    }

    /**
     * Recupera todas as categorias de produtos.
     *
     * @return Uma lista de todas as categorias de produtos no sistema.
     */
    @GetMapping("/all")
    public ResponseEntity<Iterable<ProductCategory>> getAllProductCategories() {
        Iterable<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        return ResponseEntity.status(HttpStatus.OK).body(productCategories);
    }

    /**
     * Recupera uma categoria de produtos pelo seu ID.
     *
     * @param id O ID da categoria de produtos a ser recuperada.
     * @return A categoria de produtos com o ID especificado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable Long id) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productCategory);
    }

    /**
     * Atualiza uma categoria de produtos existente.
     *
     * @param productCategory A categoria de produtos atualizada.
     * @return A categoria de produtos atualizada.
     */
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<ProductCategory> updateProductCategory(@RequestBody ProductCategory productCategory) {
        ProductCategory updatedProductCategory = productCategoryService.updateProductCategory(productCategory);
        return ResponseEntity.status(200).body(updatedProductCategory);
    }

    /**
     * Exclui uma categoria de produtos pelo seu ID.
     *
     * @param id O ID da categoria de produtos a ser excluída.
     * @return Um mapa com informações sobre a operação de exclusão.
     */
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteProductCategory(@PathVariable Long id) {
        Map<String, String> response = productCategoryService.deleteProductCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}