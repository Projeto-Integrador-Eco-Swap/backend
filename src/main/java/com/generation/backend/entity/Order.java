package com.generation.backend.entity;


import com.generation.backend.entity.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "order")
@Table(
        name = "tb_Order",
        schema = "db_ecoswap"
)
public class Order {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "order_sequence"
    )
    @Column(
            name = "order_id",
            columnDefinition = "BIGINT UNSIGNED"
    )
    @EqualsAndHashCode.Include
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

    @OneToMany(mappedBy = "order")
    @Column(name = "itens",
            nullable = false,
            columnDefinition = "SET")
    private Set<OrderItem> items = new HashSet<>();

    @Column(name = "id_card",
            nullable = false,
            columnDefinition = "BIGINT UNSIGNED")
    private Long idCard;

    /**
     * Calcula o valor total do pedido somando os preços dos itens do pedido.
     *
     * @return O valor total do pedido como um objeto BigDecimal.
     *
     * @implNote Este método itera sobre os itens do pedido e calcula o valor total multiplicando o preço de cada item pela quantidade.
     * Em seguida, soma esses valores para obter o valor total do pedido.

     * @see OrderItem
     */
    public BigDecimal getTotalOrderPrice() {
        return getItems().stream()
                .map(itemPedido -> itemPedido.getProduct().getPrice().multiply(new BigDecimal(itemPedido.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"id\": " + id + ",\n" +
                "\t\"orderTrackingNumber\": \"" + orderTrackingNumber + "\",\n" +
                "\t\"totalQuantity\": " + totalQuantity + ",\n" +
                "\t\"totalPrice\": " + totalPrice + ",\n" +
                "\t\"orderStatus\": \"" + orderStatus + "\",\n" +
                "\t\"dateCreated\": \"" + dateCreated + "\",\n" +
                "\t\"lastUpdated\": \"" + lastUpdated + "\",\n" +
                "\t\"items\": [\n" + getOrderItemsAsString() + "\n\t],\n" +
                "\t\"idCard\": " + idCard + "\n" +
                "}";
    }

    /**
     * Obtém uma representação em string dos itens do pedido, separados por vírgula e quebra de linha.
     *
     * @return Uma string contendo as representações em string dos itens do pedido, separados por vírgula e quebra de linha.
     *
     * @implNote Este método utiliza a funcionalidade de stream para mapear cada item do pedido para sua representação em string
     * usando o método `toString` de `OrderItem`. Em seguida, coleta essas representações em uma única string, separando cada item por uma vírgula e quebra de linha.

     * @see OrderItem
     */
    private @NotNull String getOrderItemsAsString() {
        return items.stream()
                .map(OrderItem::toString)
                .collect(Collectors.joining(",\n"));
    }
}