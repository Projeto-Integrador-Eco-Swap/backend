package com.generation.ecoswap.repository;

import com.generation.ecoswap.entity.Categoria;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    @DisplayName("Cadastrar Categoria")
    void cadastrarCategoria() {

        Categoria categoria = new Categoria();
        categoria.setTitulo("Eletrônicos");
        categoria.setDescricao("Produtos eletrônicos");
        categoriaRepository.save(categoria);
        System.out.println(categoria);
    }

    @Test
    @DisplayName("Consultar Categoria")
    void consultarCategoria() {
        Categoria categoria = categoriaRepository.findById(9L).get();
        System.out.println(categoria);
    }

    @Test
    @DisplayName("Consultar todas as categorias")
    void consultaTodasCategorias() {
        for (Categoria categoria : categoriaRepository.findAll()) {
            System.out.println(categoria);
        }
    }

    @Test
    @DisplayName("Atualizar Categoria")
    void atualizarCategoria() {
        Categoria categoria = categoriaRepository.findById(9L).get();
        categoria.setTitulo("Eletrônicos");
        categoriaRepository.save(categoria);
        System.out.println(categoria);
    }

    @Test
    @Transactional
    @DisplayName("Deletar Categoria")
    void deletarCategoria() {
        categoriaRepository.deleteById(9L);
    }
}