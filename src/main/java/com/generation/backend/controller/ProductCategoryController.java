package com.generation.backend.controller;

import com.generation.backend.entity.ProductCategory;
import com.generation.backend.exception.InvalidIdProductCategoryException;
import com.generation.backend.exception.InvalidNameProductCategoryException;
import com.generation.backend.exception.InvalidProductCategoryException;
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

import java.util.List;
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
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) throws InvalidNameProductCategoryException {
        ProductCategory createdProductCategory = productCategoryService.createProductCategory(productCategory);
        return ResponseEntity.status(HttpStatus.OK).body(createdProductCategory);
    }

    /**
     * Cria várias categorias de produtos de uma vez.
     *
     * @param productCategories Uma lista de categorias de produtos a serem criadas.
     * @return A lista de categorias de produtos criadas.
     */
    @PostMapping("/create-multiple")
    public ResponseEntity< Iterable<ProductCategory>> createMultipleProductCategories(@RequestBody List<ProductCategory> productCategories) throws InvalidProductCategoryException {
        Iterable<ProductCategory> createdProductCategories = productCategoryService.createMultipleProductCategories(productCategories);
        return ResponseEntity.status(HttpStatus.OK).body(createdProductCategories);
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
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable Long id) throws InvalidIdProductCategoryException {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productCategory);
    }

    /**
     * Recupera uma categoria de produtos pelo seu nome.
     *
     * @param name O nome da categoria de produtos a ser recuperada.
     * @return A categoria de produtos com o nome especificado.
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<ProductCategory> getProductCategoryByName(@PathVariable String name) {
        ProductCategory productCategory = productCategoryService.getProductCategoryByName(name);
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
    public ResponseEntity<ProductCategory> updateProductCategory(@RequestBody ProductCategory productCategory) throws InvalidIdProductCategoryException {
        ProductCategory updatedProductCategory = productCategoryService.updateProductCategory(productCategory);
        return ResponseEntity.status(200).body(updatedProductCategory);
    }

    @PutMapping("/update-description")
    @Transactional
    public ResponseEntity<ProductCategory> updateProductCategoryDescription(@RequestBody ProductCategory productCategory) throws InvalidIdProductCategoryException {
        ProductCategory updatedProductCategory = productCategoryService.updateProductCategoryDescription(productCategory);
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

    /**
     * Exclui todas as categorias de produtos.
     *
     * @return Um mapa com informações sobre a operação de exclusão.
     */
    @DeleteMapping("/delete-all")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteAllProductCategories() {
        Map<String, String> response = productCategoryService.deleteAllProductCategories();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Exclui uma categoria de produtos pelo seu nome.
     *
     * @param name O nome da categoria de produtos a ser excluída.
     * @return Um mapa com informações sobre a operação de exclusão.
     */
    @DeleteMapping("/delete-by-name/{name}")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteProductCategoryByName(@PathVariable String name) {
        Map<String, String> response = productCategoryService.deleteProductCategoryByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Pesquisa categorias de produtos por descrição.
     *
     * @param description A descrição a ser usada na pesquisa.
     * @return Uma lista de categorias cuja descrição contenha o texto especificado.
     */
    @GetMapping("/search-description/{description}")
    public ResponseEntity<List<ProductCategory>> searchProductCategoriesByDescription(@PathVariable String description) {
        List<ProductCategory> productCategories = productCategoryService.searchProductCategoriesByDescription(description);
        return ResponseEntity.status(HttpStatus.OK).body(productCategories);
    }

    /**
     * Retorna uma lista de categorias de produtos ordenadas por nome.
     *
     * @return Uma lista de categorias de produtos em ordem alfabética de nome.
     */
    @GetMapping("/sort-by-name")
    public ResponseEntity<List<ProductCategory>> getProductCategoriesSortedByName() {
        List<ProductCategory> sortedCategories = productCategoryService.getProductCategoriesSortedByName();
        return ResponseEntity.status(HttpStatus.OK).body(sortedCategories);
    }

    /**
     * Lista categorias de produtos por material.
     *
     * @param material O material a ser usado na filtragem.
     * @return Uma lista de categorias cujo material corresponda ao especificado.
     */
    @GetMapping("/material/{material}")
    public ResponseEntity<List<ProductCategory>> getProductCategoriesByMaterial(@PathVariable String material) {
        List<ProductCategory> categoriesByMaterial = productCategoryService.getProductCategoriesByMaterial(material);
        return ResponseEntity.status(HttpStatus.OK).body(categoriesByMaterial);
    }

    /**
     * Lista categorias de produtos por descrição exata.
     *
     * @param description A descrição exata a ser usada na filtragem.
     * @return Uma lista de categorias cuja descrição corresponda exatamente à especificada.
     */
    @GetMapping("/description-exact/{description}")
    public ResponseEntity<List<ProductCategory>> getProductCategoriesByExactDescription(@PathVariable String description) {
        List<ProductCategory> categoriesByExactDescription = productCategoryService.getProductCategoriesByExactDescription(description);
        return ResponseEntity.status(HttpStatus.OK).body(categoriesByExactDescription);
    }
}