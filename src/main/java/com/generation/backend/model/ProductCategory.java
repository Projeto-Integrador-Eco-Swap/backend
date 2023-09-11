package com.generation.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a product category.
 */
@Getter
@Setter
@Data
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
    
    @Column(name = "name",
            nullable = false,
            columnDefinition = "varchar(100)")
    private String name;

    @Column(name = "description",
            nullable = false,
            columnDefinition = "varchar(300)")
    private String description;

    @Column(name = "material",
            nullable = false,
            columnDefinition = "varchar(150)")
    private String material;
}