package com.generation.ecoswap.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuarios")
@Table(
        name = "tb_usuario",
        schema = "db_ecoswap"
)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String nome;

    @Email(message = "O Atributo Usuário deve ser um email válido!")
    @Column(columnDefinition = "VARCHAR(255)",
            nullable = false,
            unique = true)
    @Schema(example = "email@email.com.br")
    private String usuario;

    @Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
    @Column(columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String senha;

    @Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caracteres")
    @Column(columnDefinition = "VARCHAR(5000)")
    private String foto;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "usuario",
            cascade = CascadeType.REMOVE
    )
    @JsonIgnoreProperties("usuario")
    private List<Produto> produto;

    public Usuario(long l, String root, String mail, String rootroot, String space) {
        this.id = l;
        this.nome = root;
        this.usuario = mail;
        this.senha = rootroot;
        this.foto = space;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"id\": " + id + ",\n" +
                "\t\"nome\": \"" + nome + "\",\n" +
                "\t\"usuario\": \"" + usuario + "\",\n" +
                "\t\"senha\": \"" + senha + "\",\n" +
                "\t\"foto\": \"" + foto + "\"\n" +
                "}";
    }
}