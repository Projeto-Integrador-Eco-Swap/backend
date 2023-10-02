package com.generation.backend.entity.Enums;

import jakarta.persistence.Table;

/**
 * Enumeração que representa os possíveis estados de um pedido.
 */
@Table(name = "tb_order_status")
public enum OrderStatus {
    WAITING_PAYMENT, // Aguardando pagamento
    PAID,           // Pago
    SHIPPED,        // Enviado
    DELIVERED,      // Entregue
    CANCELED        // Cancelado
}
