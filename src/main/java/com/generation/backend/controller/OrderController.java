package com.generation.backend.controller;

import com.generation.backend.entity.Order;
import com.generation.backend.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável por lidar com as operações relacionadas a pedidos.
 */
@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

    /**
     * O serviço de pedidos a ser injetado.
     */
    private final OrderService orderService;

    /**
     * Cria um novo controlador de pedidos.
     *
     * @param orderService O serviço de pedidos a ser injetado.
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Cria um novo pedido.
     *
     * @param order O pedido a ser criado.
     * @return Uma resposta HTTP com o pedido criado.
     */
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    /**
     * Cria vários pedidos de uma vez.
     *
     * @param orders Uma coleção de pedidos a serem criados.
     * @return Uma resposta HTTP com os pedidos criados.
     */
    @PostMapping("/create-multiple")
    public ResponseEntity<Iterable<Order>> createMultipleOrders(@RequestBody Iterable<Order> orders) {
        Iterable<Order> createdOrders = orderService.createMultipleOrders(orders);
        return ResponseEntity.ok(createdOrders);
    }

    /**
     * Obtém um pedido pelo seu ID.
     *
     * @param id O ID do pedido a ser obtido.
     * @return Uma resposta HTTP com o pedido encontrado.
     */
    @GetMapping("/read/{id}")
    public ResponseEntity<Order> readOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    /**
     * Obtém todos os pedidos.
     *
     * @return Uma resposta HTTP com uma lista de todos os pedidos.
     */
    @GetMapping("/read-all")
    public ResponseEntity<Iterable<Order>> readAllOrders() {
        Iterable<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     * Atualiza um pedido existente.
     *
     * @param order O pedido com as atualizações a serem aplicadas.
     * @return Uma resposta HTTP com o pedido atualizado.
     */
    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(order);
        return ResponseEntity.ok(updatedOrder);
    }

    /**
     * Exclui um pedido pelo seu ID.
     *
     * @param id O ID do pedido a ser excluído.
     * @return Uma resposta HTTP com uma mensagem de sucesso.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok("Order deleted successfully");
    }

    /**
     * Exclui todos os pedidos.
     *
     * @return Uma resposta HTTP com uma mensagem de sucesso.
     */
    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllOrders() {
        orderService.deleteAllOrders();
        return ResponseEntity.ok("All orders deleted successfully");
    }
}