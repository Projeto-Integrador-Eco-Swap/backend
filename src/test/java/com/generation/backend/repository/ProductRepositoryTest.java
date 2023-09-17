package com.generation.backend.repository;

import com.generation.backend.entity.Product;
import com.generation.backend.entity.ProductCategory;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

/**
 * Classe de testes para o repositório de produtos.
 *
 * <p>Esta classe utiliza a anotação {@code @SpringBootTest} para indicar que os testes
 * devem ser executados no contexto do Spring Boot. A anotação {@code @Autowired}
 * injeta uma instância de {@code ProductRepository} para ser utilizada nos testes.</p>
 *
 * <p>Os testes são executados em ordem alfabética, com os métodos nomeados
 * com um prefixo que denota a ordem de execução.</p>
 */
@SpringBootTest
public class ProductRepositoryTest {

    /**
     * Injeta uma instância de {@code ProductRepository} para ser utilizada nos testes.
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * Injeta uma instância de {@code ProductCategoryRepository} para ser utilizada nos testes.
     */
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /**
     * Testa o método `save` do repositório, que salva um objeto Product individualmente.
     */
    @Test
    @Transactional
    void saveMethod() {

        Product product1 = new Product();
        ProductCategory productCategory1 = new ProductCategory();

        productCategory1.setName("Eletrônicos");
        productCategory1.setDescription("Eletrônicos em geral");
        productCategory1.setMaterial("Metal");

        product1.setName("Iphone 15 Pro Max");
        product1.setPrice(new BigDecimal("1349.99"));
        product1.setImage("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.casasbahia.com.br%2Fcelulares%2Fsmartphone-samsung-galaxy-a01-core-32gb-azul-4g-2gb-ram-tela-5-3-cam-dupla");
        product1.setActivated(true);
        product1.setCategory(productCategory1);

        productCategoryRepository.save(productCategory1);
        productRepository.save(product1);

        System.out.println(productCategory1);
        System.out.println("------------------------------------------------");
        System.out.println(product1);

    }

    /**
     * Testa o método `saveAll` do repositório, que salva uma lista de objetos Product.
     */
    @Test
    void saveAllMethod() {

        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        ProductCategory productCategory1 = new ProductCategory();

        productCategory1.setName("Eletrônicos");
        productCategory1.setDescription("Eletrônicos em geral");
        productCategory1.setMaterial("Metal");

        product1.setName("Iphone 15 Pro Max");
        product1.setPrice(new BigDecimal("1349.99"));
        product1.setImage("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.casasbahia.com.br%2Fcelulares%2Fsmartphone-samsung-galaxy-a01-core-32gb-azul-4g-2gb-ram-tela-5-3-cam-dupla");
        product1.setActivated(true);
        product1.setCategory(productCategory1);

        product2.setName("Iphone 17 Pro Max");
        product2.setPrice(new BigDecimal("1349.99"));
        product2.setImage("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.casasbahia.com.br%2Fcelulares%2Fsmartphone-samsung-galaxy-a01-core-32gb-azul-4g-2gb-ram-tela-5-3-cam-dupla");
        product2.setActivated(true);
        product2.setCategory(productCategory1);

        product3.setName("Iphone 18 Pro Max");
        product3.setPrice(new BigDecimal("1349.99"));
        product3.setImage("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.casasbahia.com.br%2Fcelulares%2Fsmartphone-samsung-galaxy-a01-core-32gb-azul-4g-2gb-ram-tela-5-3-cam-dupla");
        product3.setActivated(true);
        product3.setCategory(productCategory1);

        productCategoryRepository.save(productCategory1);
        productRepository.saveAll(List.of(product1, product2, product3));

        System.out.println(productCategory1);
        System.out.println("------------------------------------------------");
        System.out.println(product1);
        System.out.println("------------------------------------------------");
        System.out.println(product2);
        System.out.println("------------------------------------------------");
        System.out.println(product3);
    }

    /**
     * Testa o método `findAll` do repositório, que busca todos os objetos Product.
     */
    @Test
    void findAllMethod() {
        List<Product> productList = productRepository.findAll();

        System.out.println("[");
        boolean isFirst = true;
        for (Product product : productList) {
            if (!isFirst) {
                System.out.print(",");
            } else {
                isFirst = false;
            }
            System.out.println("\t" + product.toString());
        }
        System.out.println("]");
    }

    /**
     * Testa o método `findById` do repositório, que busca um objeto Product pelo id.
     */
    @Test
    void findByIdMethod() {
        Product product = productRepository.findById(1L).orElse(null);
        System.out.println(product);
    }

    /**
     * Testa o método `findByName` do repositório, que busca um objeto Product pelo nome.
     */
    @Test
    void findByNameMethod() {
        Product product = productRepository.findByName("Iphone 15 Pro Max");
        System.out.println(product);
    }

    /**
     * Testa o método `findByCategory` do repositório, que busca todos os objetos Product por categoria.
     */
    @Test
    void findByCategoryMethod() {
        List<Product> productList = productRepository.findByCategory(productCategoryRepository.findById(1L).orElse(null));

        System.out.println("[");
        boolean isFirst = true;
        for (Product product : productList) {
            if (!isFirst) {
                System.out.print(",");
            } else {
                isFirst = false;
            }
            System.out.println("\t" + product.toString());
        }
        System.out.println("]");
    }

    /**
     * Testa o método `update` do repositório, que atualiza um objeto Product existente.
     */
    @Test
    @Transactional
    void updateMethod() {
        Product product = productRepository.findById(2L).orElse(null);

        assert product != null;
        product.setName("Iphone 15 Pro Max");
        product.setPrice(new BigDecimal("1349.99"));
        product.setImage("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.casasbahia.com.br%2Fcelulares%2Fsmartphone-samsung-galaxy-a01-core-32gb-azul-4g-2gb-ram-tela-5-3-cam-dupla");
        product.setActivated(true);
        product.setCategory(productCategoryRepository.findById(2L).orElse(null));

        productRepository.save(product);

        System.out.println(product);
    }

    @Test
    void countMethod() {
        System.out.println(productRepository.count());

    }

    /**
     * Testa o método `deleteByName` do repositório, que exclui um objeto Product pelo nome.
     */
    @Test
    @Transactional
    void deleteByNameMethod() {
        try {
            productRepository.deleteByName("Iphone 15 Pro Max");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Testa o método `deleteById` do repositório, que exclui um objeto Product pelo id.
     */
    @Test
    @Transactional
    void deleteByIdMethod() {
        try {
            productRepository.deleteById(1L);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Testa o método `deleteAll` do repositório, que exclui todos os objetos Product.
     */
    @Test
    @Transactional
    void deleteAllMethod() {
        try {
            productRepository.deleteAll();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Verifica se um produto com o ID especificado existe.
     */
    @Test
    @Transactional
    void existsByIdMethod() {
        boolean exists = productRepository.existsById(1L);
        System.out.println("Product with ID 1 exists: " + exists);
    }

    /**
     * Verifica se um produto com o nome especificado existe.
     */
    @Test
    @Transactional
    void existsByNameMethod() {
        boolean exists = productRepository.existsProductByName("Iphone 15 Pro Max");
        System.out.println("Product with name 'Iphone 15 Pro Max' exists: " + exists);
    }

    /**
     * Verifica se produtos pertencentes a uma categoria especificada existem.
     */
    @Test
    void existsByCategoryMethod() {
        ProductCategory category = productCategoryRepository.findById(1L).orElse(null);
        if (category != null) {
            boolean exists = productRepository.findByCategory(category).isEmpty();
            System.out.println("Products in category '"
                    + category.getName() + "' exist: " + !exists);
        } else {
            System.out.println("Category not found.");
        }
    }

    /**
     * Verifica se produtos com preços dentro da faixa especificada existem.
     */
    @Test
    void existsByPriceRangeMethod() {
        BigDecimal minPrice = new BigDecimal("1349.99");
        BigDecimal maxPrice = new BigDecimal("1349.99");

        boolean exists = !productRepository.findProductsByPriceRange(minPrice, maxPrice).isEmpty();
        System.out.println("Products with price between " + minPrice + " and " + maxPrice + " exist: " + exists);
    }

    /**
     * Verifica se produtos com o status de ativação especificado existem.
     */
    @Test
    void existsByActivationMethod() {
        boolean exists = !productRepository.findProductsByActive(true).isEmpty();
        System.out.println("Activated products exist: " + exists);
    }
}