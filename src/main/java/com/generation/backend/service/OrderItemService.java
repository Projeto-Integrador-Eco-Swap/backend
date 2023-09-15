package com.generation.backend.service;

import com.generation.backend.entity.OrderItem;

import java.util.List;
import java.util.Map;

/**
 * Uma interface que define os serviços relacionados aos itens de pedido.
 */
public interface OrderItemService {

    /**
     * Cria um novo item de pedido.
     *
     * @param orderItem O item de pedido a ser criado.
     * @return O item de pedido criado.
     */
    OrderItem createOrderItem(OrderItem orderItem);

    /**
     * Cria vários itens de pedido de uma só vez.
     *
     * @param orderItems Os itens de pedido a serem criados.
     * @return Uma coleção de itens de pedido criados.
     */
    Iterable<OrderItem> createMultipleOrderItems(Iterable<OrderItem> orderItems);

    /**
     * Obtém um item de pedido pelo seu ID.
     *
     * @param id O ID do item de pedido a ser obtido.
     * @return O item de pedido correspondente ao ID, ou null se não encontrado.
     */
    OrderItem getOrderItemById(Long id);

    /**
     * Obtém todos os itens de pedido.
     *
     * @return Uma lista de todos os itens de pedido.
     */
    List<OrderItem> getAllOrderItems();

    /**
     * Atualiza um item de pedido existente.
     *
     * @param orderItem O item de pedido com os dados atualizados.
     * @return O item de pedido atualizado.
     */
    OrderItem updateOrderItem(OrderItem orderItem);

    /**
     * Deleta um item de pedido pelo seu ID.
     *
     * @param id O ID do item de pedido a ser deletado.
     * @return Um mapa com uma mensagem indicando o resultado da operação.
     */
    Map<String, String> deleteOrderItemById(Long id);

    /**
     * Deleta todos os itens de pedido.
     *
     * @return Um mapa com uma mensagem indicando o resultado da operação.
     */
    Map<String, String> deleteAllOrderItems();
}
