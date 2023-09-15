package com.generation.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "order_items")
@Table
        (
                name = "tb_order_item",
                schema = "db_ecoswap"
        )
public class OrderItem {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "order_item_sequence"
    )
    @Column(name = "order_item_id",
            nullable = false,
            columnDefinition = "BIGINT UNSIGNED")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "image",
            nullable = false,
            columnDefinition = "VARCHAR(255)")
    private String imageUrl;

    @Column(name = "unit_price",
            nullable = false,
            columnDefinition = "DECIMAL(15,2) default 0.0")
    @Positive(message = "O preço deve ser positivo.")
    private BigDecimal price;

    @Column(name = "quantity",
            nullable = false,
            columnDefinition = "int default 0")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id",
            nullable = false,
            referencedColumnName = "product_id",
            foreignKey = @ForeignKey(name = "fk_order_item_product"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id",
            referencedColumnName = "order_id",
            foreignKey = @ForeignKey(name = "fk_order_item_order"))
    private Order order;
}