package com.generation.backend.service.implementation;

import com.generation.backend.entity.Order;
import com.generation.backend.repository.OrderRepository;
import com.generation.backend.service.OrderService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementação do serviço para manipulação de pedidos.
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * O repositório de pedidos.
     */
    private final OrderRepository orderRepository;

    /**
     * Cria um novo serviço de pedido.
     *
     * @param orderRepository O repositório de pedidos.
     */
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Cria um novo pedido.
     *
     * @param order O pedido a ser criado.
     * @return O pedido criado.
     */
    @Override
    public Order createOrder(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    /**
     * Obtém um pedido pelo seu ID.
     *
     * @param id O ID do pedido a ser obtido.
     * @return O pedido encontrado ou null se não existir.
     */
    @Override
    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    /**
     * Obtém todos os pedidos.
     *
     * @return Uma lista de todos os pedidos.
     */
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Atualiza um pedido existente.
     *
     * @param order O pedido com as atualizações a serem aplicadas.
     * @return O pedido atualizado.
     * @throws IllegalArgumentException se o pedido não tiver um ID.
     */
    @Override
    public Order updateOrder(@NotNull Order order) {
        if (order.getId() == null) {
            throw new IllegalArgumentException("Order to be updated must have an ID.");
        }

        Order existingOrder = orderRepository.findById(order.getId())
                .orElseThrow(() -> new IllegalArgumentException("Order with ID " + order.getId() + " not found."));

        existingOrder.setOrderTrackingNumber(order.getOrderTrackingNumber());
        existingOrder.setTotalQuantity(order.getTotalQuantity());
        existingOrder.setTotalPrice(order.getTotalPrice());
        existingOrder.setOrderStatus(order.getOrderStatus());
        existingOrder.setDateCreated(order.getDateCreated());
        existingOrder.setLastUpdated(order.getLastUpdated());
        existingOrder.setItems(order.getItems());
        existingOrder.setIdCard(order.getIdCard());

        return orderRepository.saveAndFlush(existingOrder);
    }

    /**
     * Exclui um pedido pelo seu ID.
     *
     * @param id O ID do pedido a ser excluído.
     * @return Um mapa com uma mensagem indicando que o pedido foi excluído com sucesso.
     */
    @Override
    public Map<String, String> deleteOrderById(Long id) {
        orderRepository.deleteById(id);
        return Map.of("message", "Order with ID " + id + " deleted successfully.");
    }

    /**
     * Exclui todos os pedidos.
     *
     * @return Um mapa com uma mensagem indicando que todos os pedidos foram excluídos com sucesso.
     */
    @Override
    public Map<String, String> deleteAllOrders() {
        orderRepository.deleteAll();
        return Map.of("message", "All orders deleted successfully.");
    }

    /**
     * Cria vários pedidos de uma vez.
     *
     * @param orders Uma coleção de pedidos a serem criados.
     * @return Uma coleção de pedidos criados.
     */
    @Override
    public Iterable<Order> createMultipleOrders(Iterable<Order> orders) {
        return orderRepository.saveAllAndFlush(orders);
    }
}
