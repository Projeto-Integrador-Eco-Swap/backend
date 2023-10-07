package com.generation.ecoswap.controller;

import java.util.List;
import java.util.Optional;

import com.generation.ecoswap.entity.Categoria;
import com.generation.ecoswap.repository.CategoriaRepository;
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
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    @Contract(pure = true)
    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Categoria> cadastrar(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaRepository.save(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<Optional<Categoria>> getByTitle(@PathVariable String descricao) {
        return ResponseEntity.ok(categoriaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Categoria>> getAll() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Categoria> atualizar(@Valid @RequestBody @NotNull Categoria categoria) {
        return categoriaRepository.findById(categoria.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(categoriaRepository.save(categoria)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        categoriaRepository.deleteById(id);
    }
}