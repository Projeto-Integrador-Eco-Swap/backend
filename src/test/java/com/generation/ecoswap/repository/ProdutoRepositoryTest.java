package com.generation.ecoswap.repository;

import com.generation.ecoswap.entity.Categoria;
import com.generation.ecoswap.entity.Produto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * Testes para o {@link ProdutoRepository}.
 */
@SpringBootTest
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Teste para cadastrar um produto.
     */
    @Test
    @DisplayName("Cadastrar Produto")
    @Transactional
    void cadastrarProduto() {

        Categoria categoria = new Categoria();
        categoria.setTitulo("Smartphones");
        categoria.setDescricao("Celulares de última geração");
        categoriaRepository.save(categoria);

        Produto produto = new Produto();
        produto.setTitulo("iPhone 12");
        produto.setDescricao("iPhone 12 128GB");
        produto.setPreco(new BigDecimal("7999.99"));
        produto.setCategoria(categoria);
        produtoRepository.save(produto);

        System.out.println(produto);
    }

    /**
     * Teste para consultar um produto pelo ID.
     */
    @Test
    @DisplayName("Consultar Produto")
    void consultarProduto() {
        Produto produto = produtoRepository.findById(6L).get();
        System.out.println(produto);
    }

    /**
     * Teste para consultar todos os produtos.
     */
    @Test
    @DisplayName("Consultar Todos os Produtos")
    void consultaTodosProdutos() {
        for (Produto produto : produtoRepository.findAll()) {
            System.out.println(produto);
        }
    }

    /**
     * Teste para atualizar um produto.
     */
    @Test
    @DisplayName("Atualizar Produto")
    @Transactional
    void atualizarProduto() {
        Produto produto = produtoRepository.findById(6L).get();
        produto.setPreco(new BigDecimal("8999.99"));
        produtoRepository.save(produto);
        System.out.println(produto);
    }

    /**
     * Teste para deletar um produto pelo ID.
     */
    @Test
    @DisplayName("Deletar Produto")
    void deletarProduto() {
        produtoRepository.deleteById(3L);
    }

    /**
     * Teste para deletar todos os produtos.
     */
    @Test
    @DisplayName("Deletar Todos os Produtos")
    void deletarTodosProdutos() {
        produtoRepository.deleteAll();
    }

    /**
     * Teste para consultar produtos pelo título contendo.
     */
    @Test
    @DisplayName("Consultar Produto por Título Contendo")
    void consultarPorTitulo() {
        for (Produto produto : produtoRepository.findAllByTituloContainingIgnoreCase("iphone")) {
            System.out.println(produto);
        }
    }
}
