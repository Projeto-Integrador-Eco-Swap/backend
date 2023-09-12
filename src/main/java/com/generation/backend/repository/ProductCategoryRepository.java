package com.generation.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.generation.backend.entity.ProductCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    ProductCategory findByName(@Param("name") String name);
    
    void deleteByName(String name);
}
