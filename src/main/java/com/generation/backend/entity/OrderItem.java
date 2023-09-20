package com.generation.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "order_items")
@Table(
        name = "tb_order_item",
        schema = "db_ecoswap"
)
public class OrderItem {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "order_item_sequence"
    )
    @Column(
            name = "order_item_id",
            columnDefinition = "BIGINT UNSIGNED"
    )
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
            columnDefinition = "INT default 0")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id",
            nullable = false,
            referencedColumnName = "product_id",
            columnDefinition = "BIGINT UNSIGNED",
            foreignKey = @ForeignKey(name = "fk_order_item_product"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id",
            referencedColumnName = "order_id",
            columnDefinition = "BIGINT UNSIGNED",
            foreignKey = @ForeignKey(name = "fk_order_item_order"))
    private Order order;

    @Override
    public String toString() {
        return "{\n" +
                "\t\"id\": " + id + ",\n" +
                "\t\"imageUrl\": \"" + imageUrl + "\",\n" +
                "\t\"price\": " + price + ",\n" +
                "\t\"quantity\": " + quantity + ",\n" +
                "\t\"product\": " + (product != null ? product.toString() : "null") + ",\n" +
                "\t\"order\": " + (order != null ? order.toString() : "null") + "\n" +
                "}";
    }
}