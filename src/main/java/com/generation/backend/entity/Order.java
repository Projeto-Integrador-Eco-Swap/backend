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
@Entity(name = "orders")
@Table(
        name = "tb_order",
        schema = "db_ecoSwap"
)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "order_id", //Não pode mudar essa linha por hipótese alguma
            columnDefinition = "BIGINT UNSIGNED"
    )
    @EqualsAndHashCode.Include
    private Long id;

    @Column(columnDefinition = "VARCHAR(50)",
            nullable = false)
    private String orderTrackingNumber;

    @Column(columnDefinition = "INT default 0",
            nullable = false)
    private int totalQuantity;

    @Column(columnDefinition = "DECIMAL(15,2) default 0.0",
            nullable = false)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) default 'NEW'",
            nullable = false)
    private OrderStatus orderStatus;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP",
            nullable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdated;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "order"
    )
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "address_id",
            columnDefinition = "BIGINT UNSIGNED",
            nullable = false
    )
    private Address address;

    @Column(columnDefinition = "VARCHAR(255)",
            nullable = false)
    private Long idCard;

    /**
     * Calcula o valor total do pedido somando os preços dos itens do pedido.
     *
     * @return O valor total do pedido como um objeto BigDecimal.
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