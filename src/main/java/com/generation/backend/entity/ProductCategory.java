package com.generation.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a product category.
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "productcategory")
@Table(name = "tb_product_category",
        schema = "db_ecoswap",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_productcategory_name",
                        columnNames = "name")
        },
        indexes = {
                @Index(name = "idx_productcategory_name",
                        columnList = "name")
        })
public class ProductCategory {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "productcategory_sequence")
    @Column(name = "productcategory_id",
            columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Column(name = "name",
            nullable = false,
            unique = true,
            columnDefinition = "varchar(50)")
    private String name;

    @Column(name = "description",
            nullable = false,
            columnDefinition = "varchar(255)")
    private String description;

    @Column(name = "material",
            nullable = false,
            columnDefinition = "varchar(255)")
    private String material;
}