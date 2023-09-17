package com.generation.backend.repository;

import com.generation.backend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta interface atua como um repositório de dados para a entidade OrderItem.
 * Fornece métodos de acesso a dados para realizar operações CRUD (Criar, Ler, Atualizar e Excluir)
 * em instâncias de OrderItem no banco de dados.
 *
 * @Repository Indica que esta interface é um componente de repositório gerenciado pelo Spring.
 * JpaRepository fornece métodos padrão para operações de banco de dados, como salvar, excluir e buscar.
 * Ele opera na entidade OrderItem e utiliza Long como o tipo de dado da chave primária.
 *
 * @see OrderItem
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
