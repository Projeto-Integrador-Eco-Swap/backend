package com.generation.backend.controller;


import com.generation.backend.model.Users;
import com.generation.backend.repository.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    // Método getAll()
    @GetMapping
    public ResponseEntity<List<Users>> getAll() {
        return ResponseEntity.ok(usersRepository.findAll());
    }

    // Método getId()
    @GetMapping("/{id}")
    public ResponseEntity<Users> getById(@PathVariable Long id) {
        return usersRepository.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Método GetByName
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Users>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(usersRepository.findAllByNameContainingIgnoreCase(name));
    }

    // Método Post
    @PostMapping
    public ResponseEntity<Users> post(@Valid @RequestBody Users users) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usersRepository.save(users));
    }

    // Método Put
    @PutMapping
    public ResponseEntity<Users> put(@Valid @RequestBody Users users) {
        return usersRepository.findById(users.getId())
                .map(response -> ResponseEntity.status(HttpStatus.OK)
                        .body(usersRepository.save(users)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Método Delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        Optional<Users> users = usersRepository.findById(id);

        if (users.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        usersRepository.deleteById(id);

    }
}
