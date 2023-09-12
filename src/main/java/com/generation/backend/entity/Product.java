package com.generation.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity(name = "products")
@Table(
        name = "tb_products",
        schema = "db_ecoswap",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_name",
                        columnNames = "name"
                )
        })
public class Product {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "product_sequence")
    @EqualsAndHashCode.Include
    @Column(name = "product_id",
            columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Column(name = "name",
            nullable = false,
            columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "price",
            nullable = false,
            columnDefinition = "DECIMAL(10,2)")
    @Positive(message = "O preço deve ser positivo.")
    private BigDecimal price;

    @Column(name = "image",
            nullable = false,
            columnDefinition = "VARCHAR(5000)")
    private String Url;

    @Column(name = "is_activated",
            nullable = false,
            columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isActivated;

    @CreationTimestamp
    @Column(name = "data_created",
            nullable = false,
            columnDefinition = "TIMESTAMP")
    private LocalDateTime dataCreated;

    @UpdateTimestamp
    @Column(name = "last_updated",
            nullable = false,
            columnDefinition = "TIMESTAMP")
    private LocalDateTime lastUpdated;

    @ManyToOne
    @JoinColumn(name = "productcategory_id",
            nullable = false,
            columnDefinition = "BIGINT UNSIGNED",
            foreignKey = @ForeignKey(name = "fk_product_category"))
    private ProductCategory category;
}