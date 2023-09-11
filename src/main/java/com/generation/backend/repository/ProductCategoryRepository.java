package com.generation.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.backend.model.ProductCategory;

import jakarta.validation.Valid;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {
	  List<ProductCategory> findAllByNameContainingIgnoreCase(@Param("name") String name);

	 ProductCategory updatedCategory(@Valid ProductCategory productcategory);
}
