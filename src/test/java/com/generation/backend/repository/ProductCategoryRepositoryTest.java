package com.generation.backend.repository;

import com.generation.backend.entity.ProductCategory;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

/**
 * Classe de testes para o repositório de categorias de produtos.
 *
 * <p>Esta classe utiliza a anotação {@code @SpringBootTest} para indicar que os testes
 * devem ser executados no contexto do Spring Boot. A anotação {@code @Autowired}
 * injeta uma instância de {@code ProductCategoryRepository} para ser utilizada nos testes.</p>
 *
 * <p>Os testes são executados em ordem alfabética, com os métodos nomeados
 * com um prefixo que denota a ordem de execução.</p>
 */
@SpringBootTest
public class ProductCategoryRepositoryTest {

    /**
     * Injeta uma instância de {@code ProductCategoryRepository} para ser utilizada nos testes.
     */
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /**
     * Testa o método `save` do repositório, que salva um objeto ProductCategory individualmente.
     */
    @Test
    public void saveMethod() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("Test1");
        productCategory.setDescription("Test2");
        productCategory.setMaterial("Test3");

        productCategoryRepository.save(productCategory);

        System.out.println(productCategory);
    }

    /**
     * Testa o método `saveAll` do repositório, que salva uma lista de objetos ProductCategory.
     */
    @Test
    public void saveAllMethod() {
        ProductCategory productCategory1 = new ProductCategory();
        ProductCategory productCategory2 = new ProductCategory();
        ProductCategory productCategory3 = new ProductCategory();

        productCategory1.setName("Test4");
        productCategory1.setDescription("Test5");
        productCategory1.setMaterial("Test6");

        productCategory2.setName("Test7");
        productCategory2.setDescription("Test8");
        productCategory2.setMaterial("Test9");

        productCategory3.setName("Test10");
        productCategory3.setDescription("Test11");
        productCategory3.setMaterial("Test12");

        productCategoryRepository.saveAll(List.of(productCategory1, productCategory2, productCategory3));

        System.out.println(productCategory1);
        System.out.println("--------------------------------------------------");
        System.out.println(productCategory2);
        System.out.println("--------------------------------------------------");
        System.out.println(productCategory3);
        System.out.println("--------------------------------------------------");
    }
    
    /**
     * Testa o método `findAll` do repositório, que busca todos os objetos ProductCategory.
     */
    @Test
    @DisplayName("Teste de findAll")
    public void findAllMethod() {
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll();

        System.out.println("[");
        boolean isFirst = true;
        for (ProductCategory productCategory : productCategoryList) {
            if (!isFirst) {
                System.out.print(",");
            } else {
                isFirst = false;
            }
            System.out.println(toJson(productCategory));
        }
        System.out.println("]");
    }

    /**
     * Converte um objeto ProductCategory em uma representação JSON.
     */
    private @NotNull String toJson(@NotNull ProductCategory productCategory) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n");
        jsonBuilder.append("\t\"id\": ").append(productCategory.getId()).append(",\n");
        jsonBuilder.append("\t\"name\": \"").append(productCategory.getName()).append("\",\n");
        jsonBuilder.append("\t\"description\": \"").append(productCategory.getDescription()).append("\",\n");
        jsonBuilder.append("\t\"material\": \"").append(productCategory.getMaterial()).append("\"\n");
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }


    /**
     * Testa o método `findById` do repositório, que busca um objeto ProductCategory por ID.
     */
    @Test
    public void findByIdMethod() {
        ProductCategory productCategory = productCategoryRepository.findById(1L).orElse(null);

        System.out.println(productCategory);
    }

    /**
     * Testa o método `update` do repositório, que atualiza um objeto ProductCategory existente.
     */
    @Test
    @Transactional
    public void updateMethod() {
        ProductCategory productCategory = productCategoryRepository.findById(1L).orElse(null);

        assert productCategory != null;
        productCategory.setName("Test13");
        productCategory.setDescription("Test14");
        productCategory.setMaterial("Test15");

        productCategoryRepository.save(productCategory);

        System.out.println(productCategory);
    }

    /**
     * Testa o método `findByName` do repositório, que busca um objeto ProductCategory pelo nome.
     */
    @Test
    public void findByNameMethod() {
        ProductCategory productCategory = productCategoryRepository.findByName("Test13");

        System.out.println(productCategory);
    }

    /**
     * Testa o método `count` do repositório, que conta o número de objetos ProductCategory no repositório.
     */
    @Test
    public void countMethod() {
        System.out.println(productCategoryRepository.count());
    }

    /**
     * Testa o método `deleteByName` do repositório, que exclui um objeto ProductCategory pelo nome.
     */
    @Test
    @Transactional //
    public void deleteByNameMethod() {
        try {
            productCategoryRepository.deleteByName("Test13");
            System.out.println("Deleted");
        } catch (EmptyResultDataAccessException e) {
            System.err.println("ProductCategory não encontrado pelo nome: Test13");
        }
    }

    /**
     * Testa o método `existsById` do repositório, que verifica se um objeto ProductCategory com um determinado ID existe.
     */
    @Test
    public void existsByIdMethod() {
        System.out.println(productCategoryRepository.existsById(1L));
    }

    /**
     * Testa o método `existsByName` do repositório, que verifica se um objeto ProductCategory com um determinado nome existe.
     */
    @Test
    public void existsByNameMethod() {
        System.out.println(productCategoryRepository.existsByName("Test13"));
    }

    /**
     * Testa o método `deleteById` do repositório, que exclui um objeto ProductCategory pelo ID.
     */
    @Test
    @Transactional //
    public void deleteByIdMethod() {
        try {
            productCategoryRepository.deleteById(1L);
            System.out.println("Deleted");
        } catch (EmptyResultDataAccessException e) {
            System.err.println("ProductCategory não encontrado pelo ID: 1");
        }
    }

    /**
     * Testa o método `deleteAll` do repositório, que exclui todos os objetos ProductCategory.
     */
    @Test
    @Transactional
    public void deleteAllMethod() {
        productCategoryRepository.deleteAll();
    }

    /**
     * Testa o método `searchProductCategoriesByDescription` do repositório,
     * que busca categorias de produtos por descrição.
     */
    @Test
    public void searchProductCategoriesByDescriptionMethod() {
        List<ProductCategory> productCategories = productCategoryRepository.searchProductCategoriesByDescription("Test2");
        printProductCategories(productCategories);
    }

    /**
     * Testa o método `getProductCategoriesSortedByName` do repositório,
     * que retorna uma lista de categorias de produtos ordenadas por nome.
     */
    @Test
    public void getProductCategoriesSortedByNameMethod() {
        List<ProductCategory> sortedCategories = productCategoryRepository.getProductCategoriesSortedByName();
        printProductCategories(sortedCategories);
    }

    /**
     * Testa o método `getProductCategoriesByMaterial` do repositório,
     * que lista categorias de produtos por material.
     */
    @Test
    public void getProductCategoriesByMaterialMethod() {
        List<ProductCategory> categoriesByMaterial = productCategoryRepository.getProductCategoriesByMaterial("Test3");
        printProductCategories(categoriesByMaterial);
    }

    /**
     * Imprime uma lista de objetos ProductCategory.
     */
    private void printProductCategories(@NotNull List<ProductCategory> productCategories) {
        for (ProductCategory productCategory : productCategories) {
            System.out.println(productCategory);
        }
    }
}