package com.generation.backend.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a product category.
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "productcategory")
@Table(
        name = "tb_product_category",
        schema = "db_ecoswap",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_productcategory_name",
                        columnNames = "name")
        },
        indexes = {
                @Index(name = "idx_productcategory_name",
                        columnList = "name")
        }
)
public class ProductCategory {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "productcategory_sequence")
    @Column(
            name = "productcategory_id",
            columnDefinition = "BIGINT UNSIGNED"
    )
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name",
            nullable = false,
            unique = true,
            columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "description",
            nullable = false,
            columnDefinition = "VARCHAR(255)")
    private String description;

    @Column(name = "material",
            nullable = false,
            columnDefinition = "VARCHAR(255)")
    private String material;

    @Override
    public String toString() {
        return "{\n" +
                "\t\"id\": " + id + ",\n" +
                "\t\"name\": \"" + name + "\",\n" +
                "\t\"description\": \"" + description + "\",\n" +
                "\t\"material\": \"" + material + "\"\n" +
                "}";
    }
}