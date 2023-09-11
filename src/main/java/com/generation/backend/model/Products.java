package com.generation.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity(name = "products")
@Table(name = "tb_products",
        schema = "db_ecoswap",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name", name = "unique_name")
        })
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "product_id",
            columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Column(name = "name",
            nullable = false,
            columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres.")
    private String name;

    @Column(name = "description",
            nullable = false,
            columnDefinition = "VARCHAR(300)")
    private String description;

    @Column(name = "amount",
            nullable = false,
            columnDefinition = "FLOAT UNSIGNED")
    @Positive(message = "A quantidade deve ser positiva.")
    private float amount;

    @Column(name = "price",
            nullable = false,
            columnDefinition = "DECIMAL(10,2)")
    @Positive(message = "O preço deve ser positivo.")
    private BigDecimal price;

    @Column(name = "image",
            nullable = false,
            columnDefinition = "VARCHAR(300)")
    private String image;

    @ManyToOne
    @JoinColumn(name = "productcategory_id",
            nullable = false,
            columnDefinition = "BIGINT UNSIGNED",
            foreignKey = @ForeignKey(name = "fk_product_category"))
    private ProductCategory category;
}