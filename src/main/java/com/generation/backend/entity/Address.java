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
@Entity(name = "addresses")
@Table(
        name = "tb_address",
        schema = "db_ecoSwap"
)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(columnDefinition = "VARCHAR(14)",
            nullable = false)
    @CEP
    private String cep;

    @Column(columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String logradouro;

    @Column(columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String bairro;

    @Column(columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String localidade;

    @Column(columnDefinition = "VARCHAR(2)",
            nullable = false)
    @UF
    private String uf;

    @Column(columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String ibge;

    @Column(columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String gia;

    @Column(columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String ddd;

    @Column(columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String siafi;

    @Column(columnDefinition = "VARCHAR(255)")
    private String complemento;

    /**
     * Obtém a representação em string do endereço.
     *
     * @return A representação em string do endereço.
     */
    @Override
    public String toString() {
        return "{\n" +
                "\t\"id\": " + id + ",\n" +
                "\t\"cep\": " + cep + ",\n" +
                "\t\"logradouro\": \"" + logradouro + "\",\n" +
                "\t\"bairro\": \"" + bairro + "\",\n" +
                "\t\"localidade\": \"" + localidade + "\",\n" +
                "\t\"uf\": \"" + uf + "\",\n" +
                "\t\"ibge\": \"" + ibge + "\",\n" +
                "\t\"gia\": \"" + gia + "\",\n" +
                "\t\"ddd\": \"" + ddd + "\",\n" +
                "\t\"siafi\": \"" + siafi + "\",\n" +
                "\t\"complemento\": \"" + complemento + "\"\n" +
                "}";
    }

}