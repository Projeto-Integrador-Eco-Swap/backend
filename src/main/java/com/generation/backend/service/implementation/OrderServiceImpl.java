package com.generation.backend.service.implementation;

import com.generation.backend.entity.Order;
import com.generation.backend.repository.OrderRepository;
import com.generation.backend.service.OrderService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder (Order order) {
        validateOrderForCreation(order);

        if(order.getId() != null) {
            throw new IllegalArgumentException("A ordem deve ser criada e não deve possuir um ID.");
        }

        return orderRepository.saveAndFlush(order);
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    @Override
    public Order updateOrder(Order order) {
        validateOrderIdForUpdate(order);

        Order existingOrder = findOrderById(order.getId());

        existingOrder.setName(order.getName());
        existingOrder.setDescription(order.getDescription());

        return orderRepository.saveAndFlush(existingOrder);
    }

    // ESCREVER A ATUALIZAÇÃO DA DATA DA ORDEM

    private void validateOrderIdForUpdate(@NotNull Order order) {
        if (order.getId() == null || order.getId() <= 0) {
            throw new IllegalArgumentException("A ordem para ser atualizada deve ter um ID válido.");
        }
    }

    @Override
    public Map<String, String> deleteOrderById(Long id) {
        Order order = findOrderById(id);
        orderRepository.delete(order);
        return createSuccessResponse();
    }

    @Override
    public Order getOrderByName(String name) {
        return orderRepository.findByName(name);
    }

    private Order findOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ordem não encontrada com ID " + id + ". Não foi possível excluí-la"));
    }

    private @NotNull Map<String, String> createSuccessResponse() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "ordem excluída com sucesso.");
        return response;
    }

    private void validateOrderForCreation(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("A ordem a ser criada não deve ser nula.");
        }

        if (order.getName() == null || order.getName().isEmpty()) {
            throw new IllegalArgumentException("O nome da ordem não pode estar em branco.");
        }

        if (order.getDescription() == null || order.getDescription().isEmpty()) {
            throw new IllegalArgumentException("A descrição da ordem não pode estar em branco.");
        }

    }
}
