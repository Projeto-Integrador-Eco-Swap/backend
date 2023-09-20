package com.generation.backend.entity;

import com.generation.backend.annotation.CEP;
import com.generation.backend.annotation.UF;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

/**
 * Classe que representa uma entidade de endereço.
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "address")
@Table(
        name = "tb_address",
        schema = "db_ecoSwap"
)
public class Address {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "address_sequence"
    )
    @Column(
            name = "id_address",
            columnDefinition = "BIGINT UNSIGNED"
    )
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "cep",
            columnDefinition = "VARCHAR(14)",
            nullable = false)
    @CEP
    private String cep;

    @Column(name = "logradouro",
            columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String logradouro;

    @Column(name = "bairro",
            columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String bairro;

    @Column(name = "localidade",
            columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String localidade;

    @Column(name = "uf",
            columnDefinition = "VARCHAR(2)",
            nullable = false)
    @UF
    private String uf;

    @Column(name = "ibge",
            columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String ibge;

    @Column(name = "gia",
            columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String gia;

    @Column(name = "ddd",
            columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String ddd;

    @Column(name = "siafi",
            columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String siafi;

    @Column(name = "complemento",
            columnDefinition = "VARCHAR(255)")
    private String complemento;

    /**
     * Obtém a representação em string do endereço.
     *
     * @return A representação em string do endereço.
     */
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", cep=" + cep +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                ", ibge='" + ibge + '\'' +
                ", gia='" + gia + '\'' +
                ", ddd='" + ddd + '\'' +
                ", siafi='" + siafi + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }
}