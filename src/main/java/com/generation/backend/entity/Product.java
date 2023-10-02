package com.generation.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
@Table(
        name = "tb_products",
        schema = "db_ecoSwap",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_name",
                        columnNames = "name"
                )
        },
        indexes = {
                @Index(
                        name = "idx_name",
                        columnList = "name"
                )
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "product_id",
            columnDefinition = "BIGINT UNSIGNED"
    )
    @EqualsAndHashCode.Include
    private Long id;

    @Column(columnDefinition = "VARCHAR(50)",
            nullable = false)
    private String name;

    @Column(columnDefinition = "DECIMAL(10,2)",
            nullable = false)
    @Positive(message = "O preço deve ser positivo.")
    private BigDecimal price;

    @Column(columnDefinition = "VARCHAR(5000)",
            nullable = false)
    private String image;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE",
            nullable = false)
    private boolean isActivated;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP",
            nullable = false)
    private LocalDateTime dataCreated;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP",
            nullable = false)
    private LocalDateTime lastUpdated;

    @ManyToOne
    @JoinColumn(name = "productcategory_id",
            columnDefinition = "BIGINT UNSIGNED",
            foreignKey = @ForeignKey(name = "fk_product_category"))
    private ProductCategory category;

    @Override
    public String toString() {
        return "{\n" +
                "\t\"id\": " + id + ",\n" +
                "\t\"name\": \"" + name + "\",\n" +
                "\t\"price\": " + price + ",\n" +
                "\t\"image\": \"" + image + "\",\n" +
                "\t\"isActivated\": " + isActivated + ",\n" +
                "\t\"dataCreated\": \"" + dataCreated + "\",\n" +
                "\t\"lastUpdated\": \"" + lastUpdated + "\",\n" +
                "\t\"category\": {\n" +
                "\t\t\"id\": " + category.getId() + ",\n" +
                "\t\t\"name\": \"" + category.getName() + "\",\n" +
                "\t\t\"description\": \"" + category.getDescription() + "\",\n" +
                "\t\t\"material\": \"" + category.getMaterial() + "\"\n" +
                "\t}\n" +
                "}";
    }
}