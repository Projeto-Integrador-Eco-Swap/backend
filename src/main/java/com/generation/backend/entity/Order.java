package com.generation.backend.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "order")
@Table(name = "tb_Order",
        schema = "db_ecoswap",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = "name",
                        name = "unique_name"
                )
        })
public class Order {

    @Id
    @GeneratedValue (
            strategy = GenerationType.IDENTITY,
            generator = "order_sequence"
    )
    @Column(name = "id",
            nullable = false,
            columnDefinition = "BIGINT UNSIGNED")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name",
            nullable = false,
            columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "description",
            nullable = false,
            columnDefinition = "varchar(300)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id",
            nullable = false,
            columnDefinition = "BIGINT UNSIGNED",
            foreignKey = @ForeignKey(name = "fk_user"))
    private User user;

//    @Column(name = "is_activated",
//            nullable = false,
//            columnDefinition = "BOOLEAN DEFAULT TRUE")
//    private boolean isActivated;
//
//    @CreationTimestamp
//    @Column(name = "data_created",
//            nullable = false,
//            columnDefinition = "TIMESTAMP")
//    private LocalDateTime dataCreated;
//
//    @UpdateTimestamp
//    @Column(name = "last_updated",
//            nullable = false,
//            columnDefinition = "TIMESTAMP")
//    private LocalDateTime lastUpdated;
}
