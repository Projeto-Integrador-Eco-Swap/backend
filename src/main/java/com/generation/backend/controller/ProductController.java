package com.generation.backend.controller;

import com.generation.backend.entity.Product;
import com.generation.backend.entity.ProductCategory;
import com.generation.backend.service.ProductService;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    /**
     * Cria vários produtos de uma vez.
     *
     * @param products Uma lista de produtos a serem criados.
     * @return A lista de produtos criados.
     */
    @PostMapping("/create-multiple")
    public ResponseEntity<Iterable<Product>> createMultipleProducts(@RequestBody Iterable<Product> products) {
        Iterable<Product> createdProducts = productService.createMultipleProducts(products);
        return ResponseEntity.ok(createdProducts);
    }

    /**
     * Obtém todos os produtos.
     *
     * @return Uma lista de todos os produtos.
     */
    @GetMapping("/all")
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        Iterable<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Obtém um produto pelo ID.
     *
     * @param id O ID do produto a ser obtido.
     * @return O produto com o ID especificado, ou um erro 404 se não for encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    /**
     * Obtém um produto pelo nome.
     *
     * @param name O nome do produto a ser obtido.
     * @return O produto com o nome especificado, ou um erro 404 se não for encontrado.
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Product product = productService.getProductByName(name);
        return ResponseEntity.ok(product);
    }

    /**
     * Atualiza um produto existente.
     *
     * Este endpoint permite a atualização de um produto existente com base nas informações
     * fornecidas no corpo da solicitação (JSON). O produto atualizado é retornado como resposta.
     *
     * @param product O produto com as informações atualizadas.
     * @return Um ResponseEntity contendo o produto atualizado.
     */
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        // Chama o serviço para atualizar o produto
        Product updatedProduct = productService.updateProduct(product);

        // Retorna o produto atualizado como resposta
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Atualiza o preço de um produto existente.
     *
     * Este endpoint permite a atualização do preço de um produto existente com base
     * nas informações fornecidas no corpo da solicitação (JSON). O produto atualizado
     * é retornado como resposta.
     *
     * @param product O produto com as informações atualizadas, incluindo o novo preço.
     * @return Um ResponseEntity contendo o produto atualizado com o novo preço.
     */
    @PutMapping("/update-price")
    @Transactional
    public ResponseEntity<Product> updateProductPrice(@RequestBody Product product) {
        // Chama o serviço para atualizar o preço do produto
        Product updatedProduct = productService.updateProductPrice(product);

        // Retorna o produto atualizado como resposta
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

    /**
     * Exclui todos os produtos.
     *
     * @return Um código 204 (No Content) se a exclusão for bem-sucedida, ou um erro se nenhum produto for encontrado.
     */
    @DeleteMapping("/delete-all")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteAllProducts() {
        Map<String, String> response = productService.deleteAllProducts();
        return ResponseEntity.ok(response);
    }

    /**
     * Exclui um produto pelo seu nome.
     *
     * @param name O nome do produto a ser excluído.
     * @return Um código 204 (No Content) se a exclusão for bem-sucedida, ou um erro se o produto não for encontrado.
     */
    @DeleteMapping("/delete-by-name/{name}")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteProductByName(@PathVariable String name) {
        Map<String, String> response = productService.deleteProductByName(name);
        return ResponseEntity.ok(response);
    }
    
    /**
     * Obtém uma lista de produtos pertencentes a uma categoria específica com base no objeto de categoria.
     *
     * @param category O objeto de categoria pela qual os produtos serão filtrados.
     * @return Uma lista de produtos que pertencem à categoria especificada.
     */
    @GetMapping("/by-category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable ProductCategory category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }
    
    /**
     * Retorna uma lista de produtos que estão ativados (true) ou desativados (false).
     *
     * @param isActivated Um valor booleano que indica se os produtos devem estar ativados ou desativados.
     * @return Uma lista de produtos com base no status de ativação especificado.
     */
    @GetMapping("/activated/{isActivated}")
    public ResponseEntity<List<Product>> getProductsByActivation(@PathVariable Boolean isActivated) {
        List<Product> products = productService.getProductsByActivation(isActivated);
        return ResponseEntity.ok(products);
    }

    /**
     * Retorna uma lista de produtos com preços dentro da faixa especificada.
     *
     * @param minPrice O preço mínimo dos produtos a serem filtrados (opcional).
     * @param maxPrice O preço máximo dos produtos a serem filtrados (opcional).
     * @return Uma lista de produtos que têm preços dentro da faixa especificada.
     */
    @GetMapping("/by-price")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam(name = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(name = "maxPrice", required = false) BigDecimal maxPrice) {

        List<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }
}