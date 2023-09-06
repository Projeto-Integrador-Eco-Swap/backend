package com.generation.backend.model;

import com.generation.backend.validation.CEP;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "address")
@Table(name = "tb_Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    /*@ManyToOne*/
    /*@JoinColumn*/
    @Column(name = "cep_id")
    private String cep;

    @Column(name = "street")
    @NotNull
    private String street;

    @Column(name = "neighborhood")
    @NotNull
    private String neighborhood;

    @Column(name = "locality")
    @NotNull
    private String locality;

    @Column(name = "state")
    @NotNull
    private String state;

    @Column(name = "ibge")
    @NotNull
    private String ibge;

    @Column(name = "gia")
    @NotNull
    private String gia;

    @Column(name = "ddd")
    @NotNull
    private String ddd;

    @Column(name = "siafi")
    @NotNull
    private String siafi;

    @Column(name = "complement")
    @Nullable
    private String complement;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", cep=" + cep +
                ", street='" + street + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", locality='" + locality + '\'' +
                ", state='" + state + '\'' +
                ", ibge='" + ibge + '\'' +
                ", gia='" + gia + '\'' +
                ", ddd='" + ddd + '\'' +
                ", siafi='" + siafi + '\'' +
                ", complement='" + complement + '\'' +
                '}';
    }
}
