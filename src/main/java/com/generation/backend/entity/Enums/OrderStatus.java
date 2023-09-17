package com.generation.backend.entity.Enums;

/**
 * Enumeração que representa os possíveis estados de um pedido.
 */
public enum OrderStatus {
    WAITING_PAYMENT, // Aguardando pagamento
    PAID,           // Pago
    SHIPPED,        // Enviado
    DELIVERED,      // Entregue
    CANCELED        // Cancelado
}
