package com.generation.backend.entity;

import com.generation.backend.annotation.CEP;
import com.generation.backend.annotation.UF;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * Classe que representa uma entidade de endereço.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "address")
@Table(name = "tb_address",
        schema = "db_ecoSwap")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address",
            columnDefinition = "bigint unsigned")
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

    /**
     * Verifica se dois objetos Address são iguais com base no ID.
     *
     * @param o O objeto a ser comparado.
     * @return True se os objetos são iguais, false caso contrário.
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Address address = (Address) o;
        return this.getId() != null &&
                Objects.equals(this.getId(), address.getId());
    }

    /**
     * Calcula o código de hash do objeto Address.
     *
     * @return O código de hash do objeto.
     */
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
