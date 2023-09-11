package com.generation.backend.controller;

import com.generation.backend.model.Products;
import com.generation.backend.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável por gerenciar endpoints relacionados aos produtos.
 */
@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    /**
     * O serviço para produtos.
     */
    private final ProductService productService;

    /**
     * Cria um novo controlador de produtos.
     *
     * @param productService O serviço para produtos.
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Cria um novo produto.
     *
     * @param product O produto a ser criado.
     * @return O produto criado.
     */
    @PostMapping("/create")
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        Products createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    /**
     * Obtém todos os produtos.
     *
     * @return Uma lista de todos os produtos.
     */
    @GetMapping("/all")
    public ResponseEntity<Iterable<Products>> getAllProducts() {
        Iterable<Products> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Obtém um produto pelo ID.
     *
     * @param id O ID do produto a ser obtido.
     * @return O produto com o ID especificado, ou um erro 404 se não for encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        Products product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    /**
     * Atualiza um produto existente.
     *
     * @param product O produto atualizado.
     * @return O produto atualizado.
     */
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<Products> updateProduct(@RequestBody Products product) {
        Products updatedProduct = productService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Exclui um produto pelo seu ID único.
     *
     * @param id O ID do produto a ser excluído.
     * @return Um código 204 (No Content) se a exclusão for bem-sucedida, ou um erro se o produto não for encontrado.
     */
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }
}