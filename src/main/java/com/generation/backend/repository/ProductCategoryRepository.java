package com.generation.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.generation.backend.model.ProductCategory;

import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {

	
	ProductCategory findByName(@Param("name") String name);
	
	List<ProductCategory> findAllByNameContainingIgnoreCase(@Param("name") String name);
}
