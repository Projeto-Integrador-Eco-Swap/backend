package com.generation.ecoswap.controller;

import java.util.List;
import java.util.Optional;

import com.generation.ecoswap.entity.Produto;
import com.generation.ecoswap.repository.CategoriaRepository;
import com.generation.ecoswap.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    @Contract(pure = true)
    public ProdutoController(
            ProdutoRepository produtoRepository,
            CategoriaRepository categoriaRepository
    ) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    @PostMapping("/cadastrar")
    public ResponseEntity<Produto> post(@Valid @RequestBody @NotNull Produto produto) {
        if (categoriaRepository.existsById(produto.getCategoria().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(produtoRepository.save(produto));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente", null);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(produtoRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @Transactional
    @PutMapping("/atualizar")
    public ResponseEntity<Produto> put(@Valid @RequestBody @NotNull Produto produto) {
        if (produtoRepository.existsById(produto.getId())) {

            if (categoriaRepository.existsById(produto.getCategoria().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(produtoRepository.save(produto));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        produtoRepository.deleteById(id);
    }
}