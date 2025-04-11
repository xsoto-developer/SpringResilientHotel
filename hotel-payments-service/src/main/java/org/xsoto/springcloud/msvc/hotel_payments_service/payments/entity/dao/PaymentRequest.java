package org.xsoto.springcloud.msvc.hotel_payments_service.payments.entity.dao;

import lombok.Data;

@Data
public  class PaymentRequest {
    private String reservationId;
    private double amount;

    // Getters y Setters
    // ... getters y setters
}