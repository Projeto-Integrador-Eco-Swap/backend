package com.generation.ecoswap.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "categorias")
@Table(
        name = "tb_categoria",
        schema = "db_ecoswap"
)
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @EqualsAndHashCode.Include
    private Long id;

    @Size(min = 5, message = "O título deve ter no mínimo 5 caracteres")
    @Column(columnDefinition = "VARCHAR(100)",
            nullable = false)
    private String titulo;

    @Size(min = 5, message = "A descrição deve ter no mínimo 5 caracteres")
    @Column(columnDefinition = "VARCHAR(500)",
            nullable = false)
    private String descricao;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "categoria",
            cascade = CascadeType.ALL
    )
    @JsonIgnoreProperties("categoria")
    private List<Produto> produto;

    @Override
    public String toString() {
        return "{\n" +
                "\t\"id\": " + id + ",\n" +
                "\t\"titulo\": \"" + titulo + "\",\n" +
                "\t\"descricao\": \"" + descricao + "\"\n" +
                "}";
    }
}