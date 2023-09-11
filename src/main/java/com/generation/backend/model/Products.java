package com.generation.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table (name = "tb_products")
public class Products {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message= "O atributo nome deve ser preenchido! ")
	@Size (min= 2, max= 100, message= "O atributo nome deve ter no minimo 2 a 100 caracteres")
	private String name;
	
	@NotNull(message= "A quantidade é obrigatória")
	@Positive(message= "A quantidade deve ser maior que 0")
	private int amount;

	@NotNull(message= "O preço é obrigatória")
	@Positive(message= "O preço deve ser maior que 0")
	private int price;
	
	
}
