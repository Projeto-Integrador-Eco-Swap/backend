package com.generation.ecoswap.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "produtos")
@Table(
        name = "tb_produtos",
        schema = "db_ecoswap"
)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Size(min = 5, message = "O título deve ter no mínimo 5 caracteres")
    @Column(columnDefinition = "VARCHAR(100)",
            nullable = false)
    private String titulo;

    @Size(min = 5, message = "A descrição deve ter no mínimo 5 caracteres")
    @Column(columnDefinition = "VARCHAR(500)",
            nullable = false)
    private String descricao;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false)
    private LocalDateTime data;

    @Column(columnDefinition = "VARCHAR(5000)")
    private String foto;

    @Column(columnDefinition = "DECIMAL(10,2)",
            nullable = false)
    @Positive(message = "Opa, calma! O preço precisa ser um valor positivo ;)")
    private BigDecimal preco;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JsonIgnoreProperties("produto")
    private Categoria categoria;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JsonIgnoreProperties("produto")
    private Usuario usuario;

    @Override
    public String toString() {
        return "{\n" +
                "\t\"id\": " + id + ",\n" +
                "\t\"titulo\": \"" + titulo + "\",\n" +
                "\t\"descricao\": \"" + descricao + "\",\n" +
                "\t\"data\": " + data + ",\n" +
                "\t\"foto\": \"" + foto + "\",\n" +
                "\t\"preco\": " + preco + ",\n" +
                "\t\"categoria\": " + categoria + ",\n" +
                "\t\"usuario\": " + usuario + "\n" +
                "}";
    }
}