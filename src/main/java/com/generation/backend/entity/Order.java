package com.generation.backend.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "order")
@Table
        (
                name = "tb_Order",
                schema = "db_ecoswap"
        )
public class Order {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "order_sequence"
    )
    @EqualsAndHashCode.Include
    @Column(name = "order_id",
            columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Column(name = "order_tracking_number",
            nullable = false,
            columnDefinition = "VARCHAR(50)")
    private String orderTrackingNumber;

    @Column(name = "total_quantity",
            nullable = false,
            columnDefinition = "INT default 0")
    private int totalQuantity;

    @Column(name = "total_price",
            nullable = false,
            columnDefinition = "DECIMAL(15,2) default 0.0")
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status",
            nullable = false,
            columnDefinition = "VARCHAR(15) default 'NEW'")
    private OrderStatus orderStatus;

    @CreationTimestamp
    @Column(name = "date_created",
            nullable = false,
            columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated",
            nullable = false,
            columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "order") // Verifique se "order" corresponde à propriedade na classe OrderItem
    private Set<OrderItem> items = new HashSet<>();

    @Column(name = "id_card",
            nullable = false,
            columnDefinition = "BIGINT UNSIGNED")
    private Long idCard;

    public BigDecimal getTotalOrderPrice() {
        BigDecimal amount = BigDecimal.ZERO;
        Set<OrderItem> itemPedidos = getItems();
        for (OrderItem itemPedido : itemPedidos) {
            amount = amount.add(itemPedido.getProduct().getPrice().multiply(new BigDecimal(itemPedido.getQuantity())));
        }
        return amount;
    }
}
