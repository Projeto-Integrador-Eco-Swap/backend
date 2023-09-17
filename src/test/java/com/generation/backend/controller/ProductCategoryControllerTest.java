package com.generation.backend.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.backend.entity.ProductCategory;
import com.generation.backend.exception.InvalidIdProductCategoryException;
import com.generation.backend.exception.InvalidProductCategoryException;
import com.generation.backend.service.ProductCategoryService;

/**
 * Classe de testes para o controlador de categorias de produtos (ProductCategoryController).
 * Utiliza o framework Mockito para simular o comportamento do serviço real (ProductCategoryService).
 */
@SpringBootTest
public class ProductCategoryControllerTest {

    /**
     * O controlador que você deseja testar
     */
    private ProductCategoryController controller;
    /**
     * O serviço mock que você deseja simular
     */
    private ProductCategoryService service;

    /**
     * Método executado antes de cada teste.
     * Cria um serviço mock para simular o comportamento do serviço real.
     */
    @BeforeEach
    void setUp() {
        service = Mockito.mock(ProductCategoryService.class);
        controller = new ProductCategoryController(service);
    }

    /**
     * Testa o método "createMultipleProductCategories" do controlador.
     *
     * @throws InvalidProductCategoryException Exceção lançada caso a categoria seja inválida.
     */
    @Test
    void createMultipleProductCategoriesTest() throws InvalidProductCategoryException {
        // Crie uma lista de categorias de produtos para o teste
        List<ProductCategory> categories = new ArrayList<>();
        categories.add(new ProductCategory());
        categories.add(new ProductCategory());

        when(service.createMultipleProductCategories(Mockito.anyList())).thenReturn(categories);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<Iterable<ProductCategory>> response = controller.createMultipleProductCategories(categories);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(categories, response.getBody());
    }

    /**
     * Testa o método "updateProductCategory" do controlador.
     *
     * @throws InvalidIdProductCategoryException Exceção lançada caso o ID da categoria seja inválido.
     */
    @Test
    void updateProductCategoryTest() throws InvalidIdProductCategoryException {
        // Crie uma categoria de produto para o teste
        ProductCategory category = new ProductCategory();
        category.setId(1L);

        // Configure o comportamento simulado do serviço
        when(service.updateProductCategory(Mockito.any(ProductCategory.class))).thenReturn(category);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<ProductCategory> response = controller.updateProductCategory(category);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(category, response.getBody());
    }

    /**
     * Testa o método "updateProductCategoryDescription" do controlador.
     *
     * @throws InvalidIdProductCategoryException Exceção lançada caso o ID da categoria seja inválido.
     */
    @Test
    void updateProductCategoryDescriptionTest() throws InvalidIdProductCategoryException {
        // Crie uma categoria de produto para o teste
        ProductCategory category = new ProductCategory();
        category.setId(1L);

        // Configure o comportamento simulado do serviço
        when(service.updateProductCategoryDescription(Mockito.any(ProductCategory.class))).thenReturn(category);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<ProductCategory> response = controller.updateProductCategoryDescription(category);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(category, response.getBody());
    }

    /**
     * Testa o método "deleteProductCategory" do controlador.
     */
    @Test
    void deleteProductCategoryTest() {
        // ID da categoria a ser excluída
        long categoryId = 1L;

        // Resposta simulada do serviço
        Map<String, String> responseMap = Map.of("message", "Categoria excluída com sucesso");

        // Configure o comportamento simulado do serviço
        when(service.deleteProductCategoryById(categoryId)).thenReturn(responseMap);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<Map<String, String>> response = controller.deleteProductCategory(categoryId);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(responseMap, response.getBody());
    }

    /**
     * Testa o método "deleteAllProductCategories" do controlador.
     */
    @Test
    void deleteAllProductCategoriesTest() {
        // Resposta simulada do serviço
        Map<String, String> responseMap = Map.of("message", "Todas as categorias foram excluídas com sucesso");

        // Configure o comportamento simulado do serviço
        when(service.deleteAllProductCategories()).thenReturn(responseMap);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<Map<String, String>> response = controller.deleteAllProductCategories();

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(responseMap, response.getBody());
    }

    /**
     * Testa o método "deleteProductCategoryByName" do controlador.
     */
    @Test
    void deleteProductCategoryByNameTest() {
        // Nome da categoria a ser excluída
        String categoryName = "CategoriaTeste";

        // Resposta simulada do serviço
        Map<String, String> responseMap = Map.of("message", "Categoria " + categoryName + " excluída com sucesso");

        // Configure o comportamento simulado do serviço
        when(service.deleteProductCategoryByName(categoryName)).thenReturn(responseMap);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<Map<String, String>> response = controller.deleteProductCategoryByName(categoryName);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(responseMap, response.getBody());
    }

    /**
     * Testa o método "getProductCategoryById" do controlador.
     *
     * @throws InvalidIdProductCategoryException Exceção lançada caso o ID da categoria seja inválido.
     */
    @Test
    void getProductCategoryByIdTest() throws InvalidIdProductCategoryException {
        // Crie uma categoria de produto simulada para o teste
        long categoryId = 1L;
        ProductCategory category = new ProductCategory();
        category.setId(categoryId);

        // Configure o comportamento simulado do serviço para retornar a categoria simulada
        when(service.getProductCategoryById(categoryId)).thenReturn(category);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<ProductCategory> response = controller.getProductCategoryById(categoryId);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()); // Verifica se o status da resposta é OK (200)
        Assertions.assertEquals(category, response.getBody()); // Verifica se o corpo da resposta contém a categoria simulada
    }


    /**
     * Testa o método "getProductCategoryByName" do controlador.
     */
    @Test
    void getProductCategoryByNameTest() {
        // Crie uma categoria de produto simulada para o teste
        String categoryName = "CategoriaTeste";
        ProductCategory category = new ProductCategory();
        category.setName(categoryName);

        // Configure o comportamento simulado do serviço para retornar a categoria simulada
        when(service.getProductCategoryByName(categoryName)).thenReturn(category);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<ProductCategory> response = controller.getProductCategoryByName(categoryName);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()); // Verifica se o status da resposta é OK (200)
        Assertions.assertEquals(category, response.getBody()); // Verifica se o corpo da resposta contém a categoria simulada
    }


    /**
     * Testa o método "searchProductCategoriesByDescription" do controlador.
     */
    @Test
    void searchProductCategoriesByDescriptionTest() {
        // Descrição a ser usada na pesquisa
        String description = "Descrição de Teste";

        // Crie uma lista simulada de categorias de produtos para o teste
        List<ProductCategory> categories = new ArrayList<>();
        categories.add(new ProductCategory());
        categories.add(new ProductCategory());
        categories.add(new ProductCategory());

        // Configure o comportamento simulado do serviço para retornar a lista simulada de categorias
        when(service.searchProductCategoriesByDescription(description)).thenReturn(categories);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<List<ProductCategory>> response = controller.searchProductCategoriesByDescription(description);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()); // Verifica se o status da resposta é OK (200)
        Assertions.assertEquals(categories, response.getBody()); // Verifica se o corpo da resposta contém a lista de categorias simulada
    }

    /**
     * Testa o método "getProductCategoriesSortedByName" do controlador.
     */
    @Test
    void getProductCategoriesSortedByNameTest() {
        // Crie uma lista de categorias de produtos simulada para o teste
        List<ProductCategory> categories = new ArrayList<>();
        categories.add(new ProductCategory());
        categories.add(new ProductCategory());
        categories.add(new ProductCategory());

        // Configure o comportamento simulado do serviço para retornar a lista de categorias simulada
        when(service.getProductCategoriesSortedByName()).thenReturn(categories);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<List<ProductCategory>> response = controller.getProductCategoriesSortedByName();

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()); // Verifica se o status da resposta é OK (200)
        Assertions.assertEquals(categories, response.getBody()); // Verifica se o corpo da resposta contém a lista de categorias simulada
    }


    /**
     * Testa o método "getProductCategoriesByMaterial" do controlador.
     */
    @Test
    void getProductCategoriesByMaterialTest() {
        // Material a ser usado na filtragem
        String material = "MaterialTeste";

        // Crie uma lista de categorias de produtos simulada para o teste
        List<ProductCategory> categories = new ArrayList<>();
        categories.add(new ProductCategory());
        categories.add(new ProductCategory());
        categories.add(new ProductCategory());

        // Configure o comportamento simulado do serviço para retornar a lista de categorias simulada
        when(service.getProductCategoriesByMaterial(material)).thenReturn(categories);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<List<ProductCategory>> response = controller.getProductCategoriesByMaterial(material);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()); // Verifica se o status da resposta é OK (200)
        Assertions.assertEquals(categories, response.getBody()); // Verifica se o corpo da resposta contém a lista de categorias simulada
    }


    /**
     * Testa o método "getProductCategoriesByExactDescription" do controlador.
     */
    @Test
    void getProductCategoriesByExactDescriptionTest() {
        // Descrição exata a ser usada na filtragem
        String description = "Descrição Exata";

        // Crie uma lista de categorias de produtos simulada para o teste
        List<ProductCategory> categories = new ArrayList<>();
        categories.add(new ProductCategory());
        categories.add(new ProductCategory());
        categories.add(new ProductCategory());

        // Configure o comportamento simulado do serviço para retornar a lista de categorias simulada
        when(service.getProductCategoriesByExactDescription(description)).thenReturn(categories);

        // Chame o método do controlador que você deseja testar
        ResponseEntity<List<ProductCategory>> response = controller.getProductCategoriesByExactDescription(description);

        // Verifique se a resposta está correta
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()); // Verifica se o status da resposta é OK (200)
        Assertions.assertEquals(categories, response.getBody()); // Verifica se o corpo da resposta contém a lista de categorias simulada
    }
}