package com.generation.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "productcategory")
@Table(name = "tb_product_category")
public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productcategory_id", 
	columnDefinition = "BIGINT UNSIGNED")
	private Long id;
	//nomeia a categoria//
	@Column(name = "name", 
			nullable = false, 
			columnDefinition = "varchar(100)")
	private String name;
	//descreve a categoria//
	@Column(name = "description", 
			nullable = false, 
			columnDefinition = "varchar(300)")
	private String description;
	//material que é predominante, por ex, plástico, metal, etc//
	@Column(name = "material", 
			nullable = false, 
			columnDefinition = "varchar(150)")
	private String material;
	
public ProductCategory updatedCategory(@Valid ProductCategory productcategory) {
        
        ProductCategory existingCategory = productcategoryRepository.findById(productcategory.getId());
	
}
}