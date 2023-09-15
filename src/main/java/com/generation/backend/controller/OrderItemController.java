package com.generation.backend.controller;

import com.generation.backend.entity.OrderItem;
import com.generation.backend.service.OrderItemService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável por lidar com as operações relacionadas aos itens de pedido.
 */
@RestController
@RequestMapping("/order-item")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderItemController {

    /**
     * O serviço de itens de pedido a ser injetado.
     */
    private final OrderItemService orderItemService;

    /**
     * Cria um novo controlador de itens de pedido.
     *
     * @param orderItemService O serviço de itens de pedido a ser injetado.
     */
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    /**
     * Cria um novo item de pedido.
     *
     * @param orderItem O item de pedido a ser criado.
     * @return A resposta HTTP com o item de pedido criado.
     */
    @PostMapping("/create")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem createdOrderItem = orderItemService.createOrderItem(orderItem);
        return ResponseEntity.ok(createdOrderItem);
    }

    /**
     * Cria vários itens de pedido de uma só vez.
     *
     * @param orderItems Os itens de pedido a serem criados.
     * @return A resposta HTTP com os itens de pedido criados.
     */
    @PostMapping("/create-multiple")
    public ResponseEntity<Iterable<OrderItem>> createMultipleOrderItems(@RequestBody Iterable<OrderItem> orderItems) {
        Iterable<OrderItem> createdOrderItems = orderItemService.createMultipleOrderItems(orderItems);
        return ResponseEntity.ok(createdOrderItems);
    }

    /**
     * Obtém um item de pedido pelo seu ID.
     *
     * @param id O ID do item de pedido a ser obtido.
     * @return A resposta HTTP com o item de pedido correspondente.
     */
    @GetMapping("/read/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        OrderItem orderItem = orderItemService.getOrderItemById(id);
        return ResponseEntity.ok(orderItem);
    }

    /**
     * Obtém todos os itens de pedido.
     *
     * @return A resposta HTTP com todos os itens de pedido.
     */
    @GetMapping("/read-all")
    public ResponseEntity<Iterable<OrderItem>> getAllOrderItems() {
        Iterable<OrderItem> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    /**
     * Atualiza um item de pedido existente.
     *
     * @param id        O ID do item de pedido a ser atualizado.
     * @param orderItem O item de pedido com os dados atualizados.
     * @return A resposta HTTP com o item de pedido atualizado.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody @NotNull OrderItem orderItem) {
        orderItem.setId(id);
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(orderItem);
        return ResponseEntity.ok(updatedOrderItem);
    }

    /**
     * Deleta um item de pedido pelo seu ID.
     *
     * @param id O ID do item de pedido a ser deletado.
     * @return A resposta HTTP indicando que o item de pedido foi deletado com sucesso.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItemById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Deleta todos os itens de pedido.
     *
     * @return A resposta HTTP indicando que todos os itens de pedido foram deletados com sucesso.
     */
    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> deleteAllOrderItems() {
        orderItemService.deleteAllOrderItems();
        return ResponseEntity.ok().build();
    }
}
