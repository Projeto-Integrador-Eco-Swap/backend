package com.generation.backend.service;

import com.generation.backend.entity.Order;

import java.util.List;
import java.util.Map;
/**
 * Uma interface que define os serviços relacionados aos pedidos.
 */
public interface OrderService {

    /**
     * Cria um novo pedido.
     *
     * @param order O pedido a ser criado.
     * @return O pedido criado.
     */
    Order createOrder(Order order);

    /**
     * Cria vários pedidos de uma só vez.
     *
     * @param orders Os pedidos a serem criados.
     * @return Uma coleção de pedidos criados.
     */
    Iterable<Order> createMultipleOrders(Iterable<Order> orders);

    /**
     * Obtém um pedido pelo seu ID.
     *
     * @param id O ID do pedido a ser obtido.
     * @return O pedido correspondente ao ID, ou null se não encontrado.
     */
    Order getOrderById(Long id);

    /**
     * Obtém todos os pedidos.
     *
     * @return Uma lista de todos os pedidos.
     */
    List<Order> getAllOrders();

    /**
     * Atualiza um pedido existente.
     *
     * @param order O pedido com os dados atualizados.
     * @return O pedido atualizado.
     */
    Order updateOrder(Order order);

    /**
     * Deleta um pedido pelo seu ID.
     *
     * @param id O ID do pedido a ser deletado.
     * @return Um mapa com uma mensagem indicando o resultado da operação.
     */
    Map<String, String> deleteOrderById(Long id);

    /**
     * Deleta todos os pedidos.
     *
     * @return Um mapa com uma mensagem indicando o resultado da operação.
     */
    Map<String, String> deleteAllOrders();
}
