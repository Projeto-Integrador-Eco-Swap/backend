package com.generation.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.backend.model.Products;
import com.generation.backend.model.Users;
import com.generation.backend.repository.ProductsRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders= "*")
public class ProductsController {

	
	@Autowired
	private ProductsRepository productsRepository;
	
	// Método getAll()
	@GetMapping
	public ResponseEntity<List<Products>> getAll(){
		return ResponseEntity.ok(productsRepository.findAll());
	}
	
	// Método getId()
	@GetMapping("/{Id}")
	public ResponseEntity<Products>getById(@PathVariable Long id){
		return productsRepository.findById(id)
				.map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	  // Método GetByName
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Products>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(productsRepository.findAllByNameContainingIgnoreCase(name));
    }

    // Método Post
    @PostMapping
    public ResponseEntity<Products> post(@Valid @RequestBody Products products) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productsRepository.save(products));
    }
    
    // Método Put
    @PutMapping
    public ResponseEntity<Products> put(@Valid @RequestBody Products products) {
        return 
             
    }
    
}
