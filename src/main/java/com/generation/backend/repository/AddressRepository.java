package com.generation.backend.repository;

import com.generation.backend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Query(value = "SELECT * FROM tb_address WHERE cep = ?1", nativeQuery = true)
    List<Address> findByCep(String cep);

    @Query(value = "SELECT * FROM tb_address WHERE logradouro = ?1", nativeQuery = true)
    List<Address> findByLogradouro(String logradouro);

    @Query(value = "SELECT * FROM tb_address WHERE bairro = ?1", nativeQuery = true)
    List<Address> findByBairro(String bairro);

    @Query(value = "SELECT * FROM tb_address WHERE localidade = ?1", nativeQuery = true)
    List<Address> findByLocalidade(String localidade);

    @Query(value = "SELECT * FROM tb_address WHERE uf = ?1", nativeQuery = true)
    List<Address> findByUf(String uf);
}