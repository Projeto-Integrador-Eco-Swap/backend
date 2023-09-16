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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "address")
@Table(name = "tb_address",
        schema = "db_ecoSwap")
@Builder
public class Address {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "address_sequence"
    )
    @Column(name = "id_address",
            columnDefinition = "bigint unsigned")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "cep",
            columnDefinition = "varchar(11)",
            nullable = false)
    @CEP
    private String cep;

    @Column(name = "logradouro",
            columnDefinition = "varchar(255)",
            nullable = false)
    private String logradouro;

    @Column(name = "bairro",
            columnDefinition = "varchar(255)",
            nullable = false)
    private String bairro;

    @Column(name = "localidade",
            columnDefinition = "varchar(255)",
            nullable = false)
    private String localidade;

    @Column(name = "uf",
            columnDefinition = "varchar(2)",
            nullable = false)
    @UF
    private String uf;

    @Column(name = "ibge",
            columnDefinition = "varchar(255)",
            nullable = false)
    private String ibge;

    @Column(name = "gia",
            columnDefinition = "varchar(255)",
            nullable = false)
    private String gia;

    @Column(name = "ddd",
            columnDefinition = "varchar(255)",
            nullable = false)
    private String ddd;

    @Column(name = "siafi",
            columnDefinition = "varchar(255)",
            nullable = false)
    private String siafi;

    @Column(name = "complemento",
            columnDefinition = "varchar(255)")
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