package com.generation.backend.service.implementation;

import com.generation.backend.entity.OrderItem;
import com.generation.backend.repository.OrderItemRepository;
import com.generation.backend.service.OrderItemService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementação do serviço de itens de pedido.
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    /**
     * O repositório de itens de pedido.
     */
    private final OrderItemRepository orderItemRepository;

    /**
     * Cria uma nova instância do serviço de itens de pedido.
     *
     * @param orderItemRepository O repositório de itens de pedido.
     */
    @Contract(pure = true)
    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * Cria um novo item de pedido.
     *
     * @param orderItem O item de pedido a ser criado.
     * @return O item de pedido criado.
     */
    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.saveAndFlush(orderItem);
    }

    /**
     * Cria vários itens de pedido de uma vez.
     *
     * @param orderItems Uma coleção de itens de pedido a serem criados.
     * @return Uma coleção de itens de pedido criados.
     */
    @Override
    public Iterable<OrderItem> createMultipleOrderItems(Iterable<OrderItem> orderItems) {
        return orderItemRepository.saveAllAndFlush(orderItems);
    }

    /**
     * Obtém um item de pedido pelo seu ID.
     *
     * @param id O ID do item de pedido a ser obtido.
     * @return O item de pedido encontrado ou null se não for encontrado.
     */
    @Override
    public OrderItem getOrderItemById(Long id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        return orderItem.orElse(null);
    }

    /**
     * Obtém todos os itens de pedido.
     *
     * @return Uma lista de todos os itens de pedido.
     */
    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    /**
     * Atualiza um item de pedido existente.
     *
     * @param orderItem O item de pedido com as atualizações a serem aplicadas.
     * @return O item de pedido atualizado.
     */
    @Override
    public OrderItem updateOrderItem(@NotNull OrderItem orderItem) {
        if (orderItem.getId() == null) {
            throw new IllegalArgumentException("OrderItem ID cannot be null");
        }

        OrderItem existingOrderItem = orderItemRepository.findById(orderItem.getId())
                .orElseThrow(() -> new IllegalArgumentException("OrderItem not found"));

        existingOrderItem.setImageUrl(orderItem.getImageUrl());
        existingOrderItem.setPrice(orderItem.getPrice());
        existingOrderItem.setQuantity(orderItem.getQuantity());
        existingOrderItem.setProduct(orderItem.getProduct());

        return orderItemRepository.saveAndFlush(existingOrderItem);
    }

    /**
     * Exclui um item de pedido pelo seu ID.
     *
     * @param id O ID do item de pedido a ser excluído.
     * @return Um mapa com uma mensagem de sucesso.
     */
    @Override
    public Map<String, String> deleteOrderItemById(Long id) {
        orderItemRepository.deleteById(id);
        return Map.of("message", "OrderItem deleted successfully");
    }

    /**
     * Exclui todos os itens de pedido.
     *
     * @return Um mapa com uma mensagem de sucesso.
     */
    @Override
    public Map<String, String> deleteAllOrderItems() {
        orderItemRepository.deleteAll();
        return Map.of("message", "All OrderItems deleted successfully");
    }
}