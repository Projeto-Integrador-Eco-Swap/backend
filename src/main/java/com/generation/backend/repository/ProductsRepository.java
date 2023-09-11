package com.generation.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.backend.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Long>{
	
	public List<Products> findAllByNameContainingIgnoreCase(@Param("name") String name);
}
