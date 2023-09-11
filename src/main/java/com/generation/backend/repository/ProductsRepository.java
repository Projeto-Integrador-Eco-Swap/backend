package com.generation.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.backend.model.Products;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>{
	
	Products findByName(@Param("name") String name);
	
	List<Products> findAllByNameContainingIgnoreCase(@Param("name") String name);
}
