package com.generation.backend.validation;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.generation.backend.exceptions.CepException;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe que representa um CEP (Código de Endereçamento Postal) e fornece métodos para validar e formatar CEPs.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "CEP")
@Table(name = "db_cep")
public final class CEP {

    // Expressão regular para validar formatos de CEP
    private static final String CEP_REGEX = "\\d{5}-?\\d{3}|\\d{2}\\.\\d{3}-\\d{2}|\\d{8}";
    private static final Pattern CEP_PATTERN = Pattern.compile(CEP_REGEX);

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // Inclui explicitamente o campo id
    private Long id;

    @Column(name = "cep", nullable = false)
    private String cep;

    /**
     * Construtor que verifica a validade do CEP e lança uma exceção se inválido.
     *
     * @param cep O CEP a ser validado e atribuído.
     * @throws IllegalArgumentException Se o formato do CEP não for válido ou se o CEP for nulo.
     */
    public CEP(String cep) throws CepException {
        if (cep == null) {
            throw new CepException("CEP cannot be null.");
        }
        if (!isValidCEP(cep)) {
            throw new CepException("Invalid CEP format!");
        }
        this.cep = cep;
    }

    /**
     * Verifica se um CEP é válido.
     *
     * @param cep O CEP a ser validado.
     * @return true se o CEP for válido, false caso contrário.
     */
    public static boolean isValidCEP(String cep) {
        Matcher matcher = CEP_PATTERN.matcher(cep);
        return matcher.matches();
    }

    /**
     * Formata um CEP com hífen.
     *
     * @param cep O CEP a ser formatado.
     * @return O CEP formatado.
     * @throws IllegalArgumentException Se o formato do CEP não for válido.
     */
    public static String formatCEP(String cep) throws CepException {
        if (!isValidCEP(cep)) {
            throw new CepException("Invalid CEP format!");
        }

        return cep.length() == 8
                ? cep.substring(0, 5) + "-" + cep.substring(5)
                : cep;
    }

    @Override
    public String toString() {
        try {
            return formatCEP(cep);
        } catch (CepException e) {
            throw new RuntimeException(e);
        }
    }
}
