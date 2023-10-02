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
@Entity(name = "productcategories")
@Table(
        name = "tb_product_category",
        schema = "db_ecoSwap",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_name",
                        columnNames = "name")
        },
        indexes = {
                @Index(name = "idx_productcategory_name",
                        columnList = "name")
        }
)
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "productcategory_id",
            columnDefinition = "BIGINT UNSIGNED"
    )
    @EqualsAndHashCode.Include
    private Long id;

    @Column(columnDefinition = "VARCHAR(50)",
            nullable = false,
            unique = true)
    private String name;

    @Column(columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String description;

    @Column(columnDefinition = "VARCHAR(255)",
            nullable = false)
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