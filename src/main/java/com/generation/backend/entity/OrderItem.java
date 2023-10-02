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
        schema = "db_ecoSwap"
)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "order_item_id",
            columnDefinition = "BIGINT UNSIGNED"
    )
    @EqualsAndHashCode.Include
    private Long id;

    @Column(columnDefinition = "VARCHAR(5000)",
            nullable = false)
    private String imageUrl;

    @Column(columnDefinition = "DECIMAL(15,2) default 0.0",
            nullable = false)
    @Positive(message = "O preço deve ser positivo.")
    private BigDecimal price;

    @Column(columnDefinition = "INT default 0",
            nullable = false)
    private int quantity;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_order_item_product")
    )
    private Product product;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "order_id",
            columnDefinition = "BIGINT UNSIGNED",
            foreignKey = @ForeignKey(name = "fk_order_item_order")
    )
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