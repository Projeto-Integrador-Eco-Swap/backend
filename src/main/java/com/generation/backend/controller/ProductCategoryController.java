package com.generation.backend.controller;

import java.util.List;
import java.util.Optional;

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

import com.generation.backend.model.ProductCategory;
import com.generation.backend.repository.ProductCategoryRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductCategoryController {
	@Autowired
	private ProductCategoryRepository productcategoryRepository;
	
	//Método getAll()
	@GetMapping
	public ResponseEntity<List<ProductCategory>> getAll(){
		return ResponseEntity.ok(productcategoryRepository.findAll());
	}
	
	//Método getById()
	@GetMapping("/{id}")
	public ResponseEntity<ProductCategory> getById(@PathVariable Long id){
		return productcategoryRepository.findById(id)
				.map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
				
	}
	
	//Método getByName
	@GetMapping("/name/{name}")
	public ResponseEntity<List<ProductCategory>> getByName(@PathVariable String name){
		return ResponseEntity.ok(productcategoryRepository.findAllByNameContainingIgnoreCase(name));
	}
	
	//Método Post, para criar nova categoria
	@PostMapping
	public ResponseEntity<ProductCategory> post (@Valid @RequestBody ProductCategory productcategory){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(productcategoryRepository.save(productcategory));
	}
	
	//Método Put, para atualizar categoria existente
	@PutMapping 
	public ResponseEntity<ProductCategory> update (@Valid @RequestBody ProductCategory productcategory){
	ProductCategory updatedCategory = productcategoryRepository.updatedCategory(productcategory);
	return ResponseEntity.ok(updatedCategory);
		
	}
	
	//Método Delete, para deletar categoria
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping ("/delete/{id}")
	public void delete (@PathVariable Long id) {
		Optional<ProductCategory> productcategory = productcategoryRepository.findById(id);
		if(productcategory.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		productcategoryRepository.deleteById(id);
		
	}

    public ProductCategory updatedCategory(@Valid ProductCategory productcategory) {
        
        ProductCategory existingCategory = productcategoryRepository.findById(productcategory.getId());
               

        existingAddress.setCep(updatedAddress.getCep());
        existingAddress.setLogradouro(updatedAddress.getLogradouro());
        existingAddress.setBairro(updatedAddress.getBairro());
        existingAddress.setLocalidade(updatedAddress.getLocalidade());
        existingAddress.setUf(updatedAddress.getUf());
        existingAddress.setIbge(updatedAddress.getIbge());
        existingAddress.setGia(updatedAddress.getGia());
        existingAddress.setDdd(updatedAddress.getDdd());
        existingAddress.setSiafi(updatedAddress.getSiafi());
        existingAddress.setComplemento(updatedAddress.getComplemento());

        return addressRepository.save(existingAddress);
    }

	}


