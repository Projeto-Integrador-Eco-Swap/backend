package com.generation.ecoswap.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "Sua categoria precisa de um título!" )
    @Size(min = 5)
    private String titulo;

    @NotBlank (message = "A categoria precisa ter uma descrição!")
    @Size(min = 5)
    private String descricao;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "categoria",
            cascade = CascadeType.ALL
    )
    @JsonIgnoreProperties("categoria")
    private List<Produto> produto;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}