package com.generation.ecoswap.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLogin {

    private Long id;
    private String nome;
    private String usuario;
    private String senha;
    private String foto;
    private String token;
}
