package com.generation.backend.repository;

import com.generation.backend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta interface atua como um repositório de dados para a entidade Address.
 * Fornece métodos de acesso a dados para realizar operações CRUD (Criar, Ler, Atualizar e Excluir)
 * em instâncias de Address no banco de dados.
 *
 * @Repository Indica que esta interface é um componente de repositório gerenciado pelo Spring.
 * JpaRepository fornece métodos padrão para operações de banco de dados, como salvar, excluir e buscar.
 * Ele opera na entidade Address e utiliza Long como o tipo de dado da chave primária.
 *
 * @see Address
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}