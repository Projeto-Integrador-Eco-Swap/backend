package com.generation.backend.controller;

import com.generation.backend.entity.Product;
import com.generation.backend.entity.ProductCategory;
import com.generation.backend.service.ProductService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Classe de testes para o controlador de produtos.
 */
@SpringBootTest
public class ProductControllerTest {

    /**
     * O controlador de produtos.
     */
    private ProductController controller;
    /**
     * O serviço para produtos.
     */
    private ProductService service;

    /**
     * Configura o ambiente de testes antes de cada teste.
     */
    @BeforeEach
    void setUp() {
        service = Mockito.mock(ProductService.class);
        controller = new ProductController(service);
    }

    /**
     * Testa o método "createProduct" do controlador de produtos.
     */
    @Test
    void createProductTest() {
        Product productToCreate = new Product();
        productToCreate.setName("Produto Teste");
        productToCreate.setPrice(BigDecimal.valueOf(10.99));
        productToCreate.setImage("https://www.google.com.br");
        productToCreate.setActivated(true);

        when(service.createProduct(productToCreate)).thenReturn(productToCreate);

        ResponseEntity<Product> response = controller.createProduct(productToCreate);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(productToCreate, response.getBody());
    }

    /**
     * Testa o método "createMultipleProducts" do controlador de produtos.
     */
    @Test
    void createMultipleProductsTest() {
        List<Product> productsToCreate = new ArrayList<>();

        Product product1 = createMockProduct("Produto 1");
        productsToCreate.add(product1);

        Product product2 = createMockProduct("Produto 2");
        productsToCreate.add(product2);

        when(service.createMultipleProducts(productsToCreate)).thenReturn(productsToCreate);
        ResponseEntity<Iterable<Product>> response = controller.createMultipleProducts(productsToCreate);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifique se a lista de produtos na resposta corresponde à lista de produtos simulados
        Iterable<Product> createdProducts = response.getBody();
        assert createdProducts != null;
        Assertions.assertNotNull(createdProducts);

        // Verifique se cada produto simulado está presente na resposta
        for (Product product : productsToCreate) {
            Assertions.assertTrue(isProductInList(product, createdProducts));
        }
    }

    /**
     * Testa o método "getAllProducts" do controlador de produtos.
     */
    @Test
    void getAllProductsTest() {

        List<Product> products = Arrays.asList(
                createMockProduct("Produto 1"),
                createMockProduct("Produto 2"),
                createMockProduct("Produto 3")
        );

        when(service.getAllProducts()).thenReturn(products);
        ResponseEntity<Iterable<Product>> response = controller.getAllProducts();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Iterable<Product> retrievedProducts = response.getBody();
        assert retrievedProducts != null;
        Assertions.assertNotNull(retrievedProducts);

        int expectedSize = products.size();
        int actualSize = 0;
        for (Product ignored : retrievedProducts) {
            actualSize++;
        }
        Assertions.assertEquals(expectedSize, actualSize);

        for (Product simulatedProduct : products) {
            assertTrue(
                    isProductInList(
                            simulatedProduct,
                            retrievedProducts
                    )
            );
        }
    }

    /**
     * Cria um produto simulado com um nome específico.
     *
     * @param name O nome do produto simulado.
     * @return O produto simulado criado.
     */
    private @NotNull Product createMockProduct(String name) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(BigDecimal.valueOf(10.99));
        product.setImage("https://www.google.com.br");
        product.setActivated(true);
        product.setCategory(new ProductCategory());
        return product;
    }

    /**
     * Verifica se um produto está presente em uma lista de produtos.
     *
     * @param product     O produto a ser verificado.
     * @param productList A lista de produtos onde verificar a presença.
     * @return true se o produto estiver presente na lista, caso contrário, false.
     */
    private boolean isProductInList(Product product,
                                    @NotNull Iterable<Product> productList) {
        for (Product p : productList) {
            if (p.getName().equals(product.getName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * Testa o método "getProductById" do controlador de produtos.
     */
    @Test
    void getProductByIdTest() {
        Long productId = 1L;

        Product simulatedProduct = createMockProduct("Produto de Teste");
        simulatedProduct.setId(productId);

        when(service.getProductById(productId)).thenReturn(simulatedProduct);

        ResponseEntity<Product> response = controller.getProductById(productId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Product retrievedProduct = response.getBody();
        assert retrievedProduct != null;
        Assertions.assertNotNull(retrievedProduct);

        Assertions.assertEquals(productId, retrievedProduct.getId());
        Assertions.assertEquals(simulatedProduct.getName(), retrievedProduct.getName());
        Assertions.assertEquals(simulatedProduct.getPrice(), retrievedProduct.getPrice());
        Assertions.assertEquals(simulatedProduct.getImage(), retrievedProduct.getImage());
        Assertions.assertEquals(simulatedProduct.isActivated(), retrievedProduct.isActivated());

        Assertions.assertEquals(simulatedProduct.getCategory(), retrievedProduct.getCategory());
    }


    @Test
    void getProductByNameTest() {
        // Crie um nome de produto simulado para o teste
        String productName = "Produto de Teste";

        // Crie um produto simulado com o nome especificado
        Product simulatedProduct = createMockProduct(productName);

        // Simule o comportamento do serviço para retornar o produto simulado pelo nome
        when(service.getProductByName(productName)).thenReturn(simulatedProduct);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<Product> response = controller.getProductByName(productName);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifique se o produto na resposta corresponde ao produto simulado
        Product retrievedProduct = response.getBody();
        assert retrievedProduct != null;
        Assertions.assertEquals(simulatedProduct, retrievedProduct);
    }

    @Test
    void updateProductTest() {
        // Crie um produto simulado para o teste
        Product productToUpdate = createMockProduct("Produto de Teste");

        // Simule o comportamento do serviço para retornar o produto atualizado
        when(service.updateProduct(productToUpdate)).thenReturn(productToUpdate);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<Product> response = controller.updateProduct(productToUpdate);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifique se o produto na resposta corresponde ao produto simulado
        Product updatedProduct = response.getBody();
        assert updatedProduct != null;
        Assertions.assertEquals(productToUpdate, updatedProduct);
    }

    @Test
    void updateProductPriceTest() {
        // Crie um produto simulado para o teste
        Product productToUpdate = createMockProduct("Produto de Teste");

        // Simule o comportamento do serviço para retornar o produto atualizado com o preço
        when(service.updateProductPrice(productToUpdate)).thenReturn(productToUpdate);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<Product> response = controller.updateProductPrice(productToUpdate);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifique se o produto na resposta corresponde ao produto simulado
        Product updatedProduct = response.getBody();
        assert updatedProduct != null;
        Assertions.assertEquals(productToUpdate, updatedProduct);
    }

    /**
     * Testa o método "deleteProductById" do controlador de produtos.
     */
    @Test
    void deleteProductByIdTest() {
        // Crie um ID simulado para um produto existente
        Long productId = 1L;

        // Simule o comportamento do serviço para excluir o produto pelo ID
        when(service.deleteProductById(productId)).thenReturn(Collections.singletonMap("message", "Produto excluído com sucesso."));

        // Chame o método do controlador que você deseja testar
        ResponseEntity<?> response = controller.deleteProductById(productId);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Testa o método "deleteAllProducts" do controlador de produtos.
     */
    @Test
    void deleteAllProductsTest() {
        // Simule o comportamento do serviço para excluir todos os produtos
        when(service.deleteAllProducts()).thenReturn(Collections.singletonMap("message", "Todos os produtos foram excluídos com sucesso."));

        // Chame o método do controlador que você deseja testar
        ResponseEntity<Map<String, String>> response = controller.deleteAllProducts();

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertTrue(Objects.requireNonNull(response.getBody()).containsKey("message"));
        Assertions.assertEquals("Todos os produtos foram excluídos com sucesso.", response.getBody().get("message"));
    }

    /**
     * Testa o método "deleteProductByName" do controlador de produtos.
     */
    @Test
    void deleteProductByNameTest() {
        // Crie um nome simulado para um produto existente
        String productName = "Produto de Teste";

        // Simule o comportamento do serviço para excluir o produto pelo nome
        when(service.deleteProductByName(productName)).thenReturn(Collections.singletonMap("message", "Produto excluído com sucesso."));

        // Chame o método do controlador que você deseja testar
        ResponseEntity<Map<String, String>> response = controller.deleteProductByName(productName);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertTrue(Objects.requireNonNull(response.getBody()).containsKey("message"));
        Assertions.assertEquals("Produto excluído com sucesso.", response.getBody().get("message"));
    }


    /**
     * Testa o método "getProductsByCategory" do controlador de produtos.
     */
    @Test
    void getProductsByCategoryTest() {
        // Crie uma categoria simulada para filtrar os produtos
        ProductCategory category = new ProductCategory();
        category.setId(1L);
        category.setName("Categoria de Teste");

        // Crie uma lista de produtos simulados que pertencem à categoria
        List<Product> products = new ArrayList<>();
        products.add(createMockProductCategory("Produto 1", category));
        products.add(createMockProductCategory("Produto 2", category));

        // Simule o comportamento do serviço para retornar os produtos filtrados pela categoria
        when(service.getProductsByCategory(category)).thenReturn(products);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<List<Product>> response = controller.getProductsByCategory(category);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifique se a lista de produtos na resposta corresponde à lista de produtos simulados
        List<Product> retrievedProducts = response.getBody();
        assert retrievedProducts != null;
        Assertions.assertNotNull(retrievedProducts);
        Assertions.assertEquals(products.size(), retrievedProducts.size());

        // Verifique se todos os produtos na lista pertencem à categoria especificada
        for (Product product : retrievedProducts) {
            Assertions.assertEquals(category, product.getCategory());
        }
    }

    /**
     * Cria um produto simulado com um nome específico e uma categoria especificada.
     *
     * @param name     O nome do produto simulado.
     * @param category A categoria do produto simulado.
     * @return O produto simulado criado.
     */
    private @NotNull Product createMockProductCategory(String name, ProductCategory category) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(BigDecimal.valueOf(10.99));
        product.setImage("https://www.google.com.br");
        product.setActivated(true);
        product.setCategory(category);
        return product;
    }

    /**
     * Testa o método "getProductsByActivation" do controlador de produtos.
     */
    @Test
    void getProductsByActivationTest() {
        // Crie um valor booleano simulado para filtrar os produtos ativados
        Boolean isActivated = true;

        // Crie uma lista de produtos simulados que estão ativados
        List<Product> products = new ArrayList<>();
        products.add(createMockProduct("Produto 1"));
        products.add(createMockProduct("Produto 2"));

        // Simule o comportamento do serviço para retornar os produtos filtrados pelo status de ativação
        when(service.getProductsByActivation(isActivated)).thenReturn(products);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<List<Product>> response = controller.getProductsByActivation(isActivated);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifique se a lista de produtos na resposta corresponde à lista de produtos simulados
        List<Product> retrievedProducts = response.getBody();
        assert retrievedProducts != null;
        Assertions.assertNotNull(retrievedProducts);
        Assertions.assertEquals(products.size(), retrievedProducts.size());

        // Verifique se todos os produtos na lista estão ativados
        for (Product product : retrievedProducts) {
            Assertions.assertTrue(product.isActivated());
        }
    }
}
